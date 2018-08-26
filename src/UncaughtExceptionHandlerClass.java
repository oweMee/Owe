// j
public class UncaughtExceptionHandlerClass implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		StackTraceElement[] trace = e.getStackTrace();
		String location = "";
		for (int i = 0; i < trace.length; i++) {
			location += trace[i].toString();
			location += "\r\n";
		}
		LoggingClass.makeLog(e.getMessage() + " " + location, 'e');
	}

}
