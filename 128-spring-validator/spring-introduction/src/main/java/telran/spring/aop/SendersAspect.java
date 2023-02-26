package telran.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import static telran.spring.aop.PointCutConstants.*;
@Component
@Aspect
public class SendersAspect {
	@Value("${app.aspect.prefix: Tel-Ran}")
	String prefix;
static Logger LOG = LoggerFactory.getLogger("aop-telran");
@Around(value = SEND_POINT_CUT)
Object logger(ProceedingJoinPoint jp) throws Throwable {
	LOG.debug("class: {} method: {}",jp.getTarget().getClass().getSimpleName(),
			jp.getSignature().getName());
	Object[] args = jp.getArgs();
	args[0] = String.format("%s\t%s", prefix, args[0]);
	return jp.proceed();
	
}
}
