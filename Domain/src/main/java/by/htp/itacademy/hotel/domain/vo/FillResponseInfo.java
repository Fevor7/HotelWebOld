package by.htp.itacademy.hotel.domain.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The object of this class will store information about the response.
 * 
 * @author viktor
 *
 */
@Data
@NoArgsConstructor
public class FillResponseInfo implements Serializable {

	private static final long serialVersionUID = 1972653796326972876L;

	private String data;
	private boolean statusMessage;

	public FillResponseInfo(boolean statusMessage) {
		super();
		this.statusMessage = statusMessage;
	}

	public FillResponseInfo(String data) {
		super();
		this.data = data;
	}

	public FillResponseInfo(String data, boolean statusMessage) {
		super();
		this.data = data;
		this.statusMessage = statusMessage;
	}

	/**
	 * Changing the value of the fields
	 * 
	 * @param data
	 * @param statusMessage
	 */
	public void changeField(String data, boolean statusMessage) {
		this.data = data;
		this.statusMessage = statusMessage;
	}
}
