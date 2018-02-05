package chapter12.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

class LoggingExcption extends Exception {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("LoggingException");

	public LoggingExcption() {
		StringWriter stringWriter = new StringWriter();
		printStackTrace(new PrintWriter(stringWriter));
		logger.severe(stringWriter.toString());
	}
}

public class LoggingExceptions {
	public static void main(String[] args) {
		try {
			throw new LoggingExcption();
		} catch (LoggingExcption e) {
			System.err.println("Caught:"+e);
		}
		try {
			throw new LoggingExcption();
		} catch (LoggingExcption e) {
			System.err.println("Caught:"+e);
		}
	}
}
