package by.htp.itacademy.hotel.command.exception;

public class CommandInvalidParameterException extends Exception {

	private static final long serialVersionUID = 405099309720111648L;

	public CommandInvalidParameterException() {
		super();
	}

	public CommandInvalidParameterException(String message) {
		super(message);
	}

	public CommandInvalidParameterException(Throwable cause) {
		super(cause);
	}

	public CommandInvalidParameterException(String message, Throwable cause) {
		super(message, cause);
	}
}
