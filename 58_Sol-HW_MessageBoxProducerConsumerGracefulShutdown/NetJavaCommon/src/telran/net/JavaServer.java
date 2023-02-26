package telran.net;

public abstract class JavaServer implements Runnable{
protected static boolean isShutdown = false;
public void shutdown() {
	isShutdown = true;
}
}
