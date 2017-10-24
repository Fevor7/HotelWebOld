package by.htp.itacademy.hotel.service.exception;

/**
 * The object of this class takes exception when there is no user in the
 * database.
 * 
 * @author Viktor
 *
 */
public class ServiceNoSuchUserException extends Exception {

	private static final long serialVersionUID = -6077611583059888479L;

	public ServiceNoSuchUserException() {
		super();
	}

	public ServiceNoSuchUserException(String message) {
		super(message);
	}

	public ServiceNoSuchUserException(Throwable cause) {
		super(cause);
	}

	public ServiceNoSuchUserException(String message, Throwable cause) {
		super(message, cause);
	}

}
