package telran.students.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.bson.Document;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.BucketAutoOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.students.dto.Mark;
import telran.students.dto.Student;
import telran.students.dto.Subject;
import telran.students.entities.StudentDoc;
import telran.students.entities.SubjectDoc;
import telran.students.entities.SubjectMark;
import telran.students.repo.*;
import telran.students.service.interfaces.*;
@Service
public class StudentsServiceImpl implements StudentsService{
private static final int MAX_MARK = 100;
private static final int MIN_MARK = 60;
StudentsRepository studentsRepository;
SubjectsRepository subjectsRepository;
MongoTemplate mongoTemplate;

	public StudentsServiceImpl(StudentsRepository studentsRepository, SubjectsRepository subjectsRepository,
		MongoTemplate mongoTemplate) {
	this.studentsRepository = studentsRepository;
	this.subjectsRepository = subjectsRepository;
	this.mongoTemplate = mongoTemplate;
}

	@Override
	public void addStudent(Student student) {
		studentsRepository.save(new StudentDoc(student.stid, student.name));
		
	}

	@Override
	public void addSubject(Subject subject) {
		subjectsRepository.save(new SubjectDoc(subject.suid, subject.subject));
		
	}

	@Override
	public Mark addMark(Mark mark) {
		StudentDoc student = studentsRepository.findById(mark.stid).orElse(null);
		if (student == null) {
			return null;
		}
		SubjectDoc subject = subjectsRepository.findById(mark.suid).orElse(null);
		if (subject == null) {
			return null;
		}
		student.getMarks().add(new SubjectMark(subject.getSubject(), mark.mark));
		studentsRepository.save(student);
		return mark;
	}

	@Override
	public List<StudentSubjectMark> getMarksStudentSubject(String name, String subject) {
		StudentDoc student =studentsRepository.findByName(name);
		if(student == null) {
			return Collections.emptyList();
		}
		
		return getStudentStream(name, subject, student).toList();
	}

	private Stream<StudentSubjectMark> getStudentStream(String name, String subject, StudentDoc student) {
		return student.getMarks().stream()
				.filter(sm -> sm.getSubject().equals(subject))
				.map(sm -> getStudentSubjectMark(sm, name));
	}
	private StudentSubjectMark getStudentSubjectMark(SubjectMark sm, String name) {
		return new StudentSubjectMark() {
			@Override
			public String getSubjectSubject() {
				return sm.getSubject();
			}
			
			@Override
			public String getStudentName() {
				return name;
			}
			@Override
			public int getMark() {
				return sm.getMark();
			}
		};
	}

	@Override
	public List<String> getBestStudents() {
		List<AggregationOperation> listOperations = getStudentAvgMark(Direction.DESC);
		double avgCollegeMark = getAvgCollegeMark();
		MatchOperation matchOperation = Aggregation.match(Criteria.where("avgMark").gt(avgCollegeMark));
		listOperations.add(matchOperation);
		return resultProcessing(listOperations, true);
	}

	@Override
	public List<String> getTopBestStudents(int nStudents) {
		List<AggregationOperation> listOperations = getStudentAvgMark(Direction.DESC);
		LimitOperation limit = Aggregation.limit(nStudents);
		listOperations.add(limit);
		return resultProcessing(listOperations, true);
	}

	private List<String> resultProcessing(List<AggregationOperation> listOperations, boolean mark) {
		try {
			List<Document> documentsRes =
					mongoTemplate.aggregate(Aggregation.newAggregation(listOperations), StudentDoc.class, Document.class)
					.getMappedResults();
			
			return documentsRes.stream().map(doc -> doc.getString("_id") + (mark ? "," +
					doc.getDouble("avgMark").intValue() : "")).toList();


		} catch (Exception e) {
			ArrayList<String> errorMessage = new ArrayList<>();
			errorMessage.add(e.getMessage());
			return errorMessage;
		}
	}

	private List<AggregationOperation> getStudentAvgMark(Direction sortDirection) {
		UnwindOperation unwindOperation = Aggregation.unwind("marks");
		GroupOperation groupOperation = Aggregation.group("name").avg("marks.mark").as("avgMark");
		SortOperation sortOperation = Aggregation.sort(sortDirection, "avgMark");
		return new ArrayList<>(Arrays.asList(unwindOperation, groupOperation, sortOperation));
	}
	private double getAvgCollegeMark() {
		UnwindOperation unwindOperation = Aggregation.unwind("marks");
		GroupOperation groupOperation = Aggregation.group().avg("marks.mark").as("avgMark");
		Aggregation pipeline = Aggregation.newAggregation(unwindOperation,groupOperation);
		return mongoTemplate.aggregate(pipeline, StudentDoc.class, Document.class)
				.getUniqueMappedResult().getDouble("avgMark");
	}

	@Override
	public List<Student> getTopBestStudentsSubject(int nStudents, String subject) {
		UnwindOperation unwindOperation = Aggregation.unwind("marks");
		MatchOperation subjectMatchOperation = Aggregation.match(Criteria.where("marks.subject")
				.is(subject));
		GroupOperation groupOperation = Aggregation.group("stid","name").avg("marks.mark").as("avgMark");
		SortOperation sortOperation = Aggregation.sort(Direction.DESC, "avgMark");
		LimitOperation limitOperation = Aggregation.limit(nStudents);
		List<Document> documents = mongoTemplate.aggregate
				(Aggregation.newAggregation(unwindOperation,
						subjectMatchOperation, groupOperation, sortOperation, limitOperation),
						StudentDoc.class, Document.class).getMappedResults();
		return documents.stream().map(this::getStudent).toList();
	}
private Student getStudent(Document doc) {
	Document groupId = (Document) doc.get("_id");
	return new Student(groupId.getInteger("stid"), groupId.getString("name"));
}
	@Override
	public List<StudentSubjectMark> getMarksOfWorstStudents(int nStudents) {
		List<AggregationOperation> listOperations = getStudentAvgMark(Direction.ASC);
		AggregationOperation limitOperation = Aggregation.limit(nStudents);
		listOperations.add(limitOperation);
		 List<String> names = resultProcessing(listOperations, false);
		 List<StudentDoc> students = studentsRepository.findByNameIn(names);
		 return students.stream().flatMap(s -> s.getMarks().stream()
					.map(sm -> getStudentSubjectMark(sm, s.getName()))).toList();
		 
	}

	@Override
	public List<IntervalMarks> marksDistibution(int interval) {
		int nIntervals = ( MAX_MARK - MIN_MARK) / interval;
		UnwindOperation unwindOperation = Aggregation.unwind("marks");
		BucketAutoOperation bucketOperation = Aggregation.bucketAuto("marks.mark", nIntervals);
		List<Document> bucketDocs =
				mongoTemplate.aggregate(Aggregation.newAggregation(unwindOperation,
						bucketOperation), StudentDoc.class, Document.class).getMappedResults();
		return bucketDocs.stream()
				.map(this::getIntervalMarks ).toList();
	}
private IntervalMarks getIntervalMarks(Document doc) {
	Document interval = (Document) doc.get("_id");
	return new IntervalMarks() {
		public int getOccurrences() {
			return doc.getInteger("count");
		}
		public int getMin() {
			return interval.getInteger("min");
		}
		public int getMax() {
			return interval.getInteger("max");
		}
	
};
}
	@Override
	public List<String> jpqlQuery(String jpql) {
		ArrayList<String> res = new ArrayList<>();
		res.add("JPQL is not supported ");
		return res;
	}

	@Override
	public List<String> nativeQuery(String queryJson) {
		List<StudentDoc> res;
		try {
			BasicQuery query = new BasicQuery(queryJson);
			res = mongoTemplate.find(query, StudentDoc.class);
			return res.stream().map(StudentDoc::toString).toList();
		} catch (Exception e) {
			ArrayList<String> errorMessage = new ArrayList<>();
			errorMessage.add(e.getMessage());
			return errorMessage;
		}
		
	}

	@Override
	@Transactional
	public List<Student> removeStudents(int avgMark, int nMarks) {
		UnwindOperation unwindOperation = Aggregation.unwind("marks");
		
		GroupOperation groupOperation = Aggregation.group("stid","name")
				.avg("marks.mark").as("avgMark").count().as("count");
		
		MatchOperation matchOperation =
				Aggregation.match(Criteria.where("avgMark")
						.lt((double)avgMark).and("count").lt((long)nMarks));
		List<Document> documents = mongoTemplate.aggregate
				(Aggregation.newAggregation(unwindOperation,
						 groupOperation, matchOperation),
						StudentDoc.class, Document.class).getMappedResults();
		List<Student> studentsForRemoving =
				documents.stream().map(this::getStudent).toList();
		studentsRepository.deleteAllById(studentsForRemoving.stream().map(s->s.stid).toList());
				
		return studentsForRemoving;
	}

}
