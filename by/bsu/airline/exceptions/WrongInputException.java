package by.bsu.airline.exceptions;

public class WrongInputException extends RuntimeException {

	public WrongInputException() {
	}

	public WrongInputException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongInputException(String message) {
		super(message);
	}

	public WrongInputException(Throwable cause) {
		super(cause);
	}

}