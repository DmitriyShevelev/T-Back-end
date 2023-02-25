package telran.spring.aop;

public interface PointCutConstants {
String SEND_POINT_CUT = "execution(public * telran.spring.service..send(..))";
}
