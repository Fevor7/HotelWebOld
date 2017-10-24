package by.htp.itacademy.hotel.service.exception;

/**
 * An exception thrown by the Service layer.
 * 
 * @author viktor
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 427293332192772775L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
