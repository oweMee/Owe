import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

// gibt den Format der Log-Datei an
public class FormatterClass extends Formatter {

	@Override
	public String format(LogRecord record) {
		String message = "";
		if (record.getLevel().intValue() == Level.INFO.intValue()) {
			message += "INFO: ";
		}
		if (record.getLevel().intValue() == Level.WARNING.intValue()) {
			message += "WARNING: ";
		}
		if (record.getLevel().intValue() == Level.SEVERE.intValue()) {
			message += "ERROR: ";
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
		Date date = new Date(record.getMillis());
		message += dateFormat.format(date);
		
		message += "\t";
		
		message += record.getMessage();
		message += "\r\n";
		return message;
	}

}
