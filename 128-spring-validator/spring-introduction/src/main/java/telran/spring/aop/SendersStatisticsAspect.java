package telran.spring.aop;

import java.util.HashMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import telran.spring.service.SenderService;

@Component
@Aspect
public class SendersStatisticsAspect {
private HashMap<String, Integer> statistics = new HashMap<>();

public HashMap<String, Integer> getStatistics() {
	return statistics;
}

@AfterReturning(value=PointCutConstants.SEND_POINT_CUT)
void updateStatistics(JoinPoint jp) {
	SenderService service = (SenderService) jp.getTarget();
	String type = service.getType();
	statistics.merge(type, 1, (a, b) -> a + b);
}
}
