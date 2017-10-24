package by.htp.itacademy.hotel.domain.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderStatus extends Unit{

	private static final long serialVersionUID = -3979309888109513400L;


	public OrderStatus(Long id, String value) {
		super(id, value);
	}

	public OrderStatus(Long id) {
		super(id);
	}

	public OrderStatus(String value) {
		super(value);
	}

	

}
