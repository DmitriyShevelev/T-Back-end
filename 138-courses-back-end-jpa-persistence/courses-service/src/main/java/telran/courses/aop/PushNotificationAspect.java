package telran.courses.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import telran.courses.api.dto.Course;

@Aspect
@Service
public class PushNotificationAspect {
	@Autowired
SimpMessagingTemplate smt;
	private Object proceed(String action, ProceedingJoinPoint jp) throws Throwable {
		Course course = (Course) jp.proceed();
		if (course != null) {
			smt.convertAndSend("/topic/courses", course.id + " " + action);
		}
		return course;
	}
	@Around("execution(* telran.courses.service..add*(..))")
	Object addCourseAdvice(ProceedingJoinPoint jp) throws Throwable{
		return proceed("add", jp);
	}
	@Around("execution(* telran.courses.service..remove*(..))")
	Object removeCourseAdvice(ProceedingJoinPoint jp) throws Throwable{
		return proceed("remove", jp);
	}
	@Around("execution(* telran.courses.service..update*(..))")
	Object updateCourseAdvice(ProceedingJoinPoint jp) throws Throwable{
		return proceed("update", jp);
	}
}
