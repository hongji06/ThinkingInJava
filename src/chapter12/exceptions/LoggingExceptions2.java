package chapter12.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class LoggingExceptions2 {
	private static Logger logger = Logger.getLogger("LoggingException2");

	static void logException(Exception e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		logger.severe(stringWriter.toString());
	}

	public static void main(String[] args) {
		try {
			throw new NullPointerException();
		} catch (NullPointerException e) {
			System.err.println("Caught:" + e);
			logException(e);
		}
	}
}
