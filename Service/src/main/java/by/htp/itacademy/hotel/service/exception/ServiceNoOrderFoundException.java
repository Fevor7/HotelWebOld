package by.htp.itacademy.hotel.service.exception;

/**
 * The exception is a pierced one, in the absence of orders.
 * 
 * @author viktor
 *
 */
public class ServiceNoOrderFoundException extends Exception {

	private static final long serialVersionUID = -4906532711784056801L;

	public ServiceNoOrderFoundException() {
		super();
	}

	public ServiceNoOrderFoundException(String message) {
		super(message);
	}

	public ServiceNoOrderFoundException(Throwable cause) {
		super(cause);
	}

	public ServiceNoOrderFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
