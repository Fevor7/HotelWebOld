package by.htp.itacademy.hotel.domain.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Unit implements Serializable {

	private static final long serialVersionUID = -8362220847929773193L;

	private Long id;
	private String value;

	public Unit(Long id) {
		super();
		this.id = id;
	}

	public Unit(String value) {
		super();
		this.value = value;
	}

}
