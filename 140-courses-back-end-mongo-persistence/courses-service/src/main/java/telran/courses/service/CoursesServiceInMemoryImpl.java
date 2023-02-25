package telran.courses.service;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import static telran.courses.api.ApiConstants.*;

import telran.courses.api.dto.Course;
//@Service
public class CoursesServiceInMemoryImpl implements CoursesService, Serializable {
static Logger LOG = LoggerFactory.getLogger(CoursesService.class);
	private static final long serialVersionUID = 1L;
	@Autowired
	transient SimpMessagingTemplate smt;
	@Value("${app.courses.fileName: courses.data}")
	private String fileName;
private Map<Integer, Course> courses = new ConcurrentHashMap<>();
@Override
public synchronized Course addCourse(Course course) {
    course.id = generateId();
    Course res = add(course);
    smt.convertAndSend("/topic/courses", "added");
    return res;
}

private Course add(Course course) {
	courses.put(course.id, course);
	return course;
}

@Override
public Course removeCourse(int id) {
   Course course = courses.remove(id);
   if (course != null) {
	      smt.convertAndSend("/topic/courses", "removed");
   }

  return course;
}

@Override
public boolean exists(int id) {
    return courses.containsKey(id);
}

@Override
public Course updateCourse(int id, Course course) {
	Course res = courses.replace(id, course);
	if (res != null) {
		smt.convertAndSend("/topic/courses", "updated");
	}
  return  res;
}

@Override
public Course getCourse(int id) {
    return courses.get(id)
;
}

@Override
public List<Course> getAllCourses() {
    
    return new ArrayList<>(courses.values());
}

@Override
public void restore() {
    try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName))) {
    	CoursesServiceInMemoryImpl res = (CoursesServiceInMemoryImpl) reader.readObject();
    	res.getAllCourses().forEach(this::add);
    	LOG.info("courses have been restored from file {}", fileName);
        
    }catch (FileNotFoundException e) {
    	LOG.info("file {} doesn't exist restore has not been performed", fileName);
    }catch (Exception e)  {
    	LOG.error("for file {} exception: {}", fileName, e);
    }
}

@Override
public void save(){
    try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName));) {
		
		writer.writeObject(this);
		LOG.info("courses have been saved into file {}", fileName);
	} catch (Exception e) {
		LOG.error("for file {} exception: {}", fileName, e);
	} 
}

private Integer generateId() {
    ThreadLocalRandom random = ThreadLocalRandom.current();
    int randomId;

    do {
        randomId = random.nextInt(MIN_ID, MAX_ID);
    } while (exists(randomId));
    return randomId;
}

}
