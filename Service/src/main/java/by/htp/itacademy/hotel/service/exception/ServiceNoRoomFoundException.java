package by.htp.itacademy.hotel.service.exception;

/**
 * The exception is pierced, in the absence of rooms.
 * 
 * @author viktor
 *
 */
public class ServiceNoRoomFoundException extends Exception {

	private static final long serialVersionUID = 8308154243756922770L;

	public ServiceNoRoomFoundException() {
		super();
	}

	public ServiceNoRoomFoundException(String message) {
		super(message);
	}

	public ServiceNoRoomFoundException(Throwable cause) {
		super(cause);
	}

	public ServiceNoRoomFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
