import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

// hier soll sich der Logger befinden
public class LoggingClass {

	private static Logger logger = createLogger();
	
	private static Logger createLogger() {
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		FileHandler logFile = null;
		try {
			logFile = new FileHandler("Log.txt", true);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logFile.setFormatter(new FormatterClass());
		logger.setLevel(Level.ALL);
		logger.addHandler(logFile);
		return logger;
	}
	
	public static void makeLog(String message, char level) {
		switch(level) {
		case 'e':
			logger.severe(message);
			break;
		case 'w':
			logger.warning(message);
			break;
		case 'i':
			logger.info(message);
			break;
		}
	}
	
	public static void makeWarningLog(String message) {
		logger.warning(message);
	}
}
