package by.htp.itacademy.hotel.dao.exception;

/**
 * An exception thrown out by the DAO layer.
 * 
 * @author viktor
 *
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = -3621365648923424080L;

	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}
