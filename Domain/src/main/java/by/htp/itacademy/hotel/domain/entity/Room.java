package by.htp.itacademy.hotel.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The object of this class stores the value of the room parameters in the
 * hotel.
 * 
 * @author viktor
 *
 */
@Data
@NoArgsConstructor
public class Room implements Serializable {

	private static final long serialVersionUID = 1080501176658140247L;
	
	private Long id;
	private Long number;
	private TypeRoom typeRoom;
	private Byte size;
	private String fotoAddress;
	private Byte person;
	private Byte bed;
	private BigDecimal price;

	
	
	public Room(Long id, BigDecimal price, Long idType) {
		super();
		this.price = price;
		this.typeRoom = new TypeRoom(idType);
	}

	public Room(Long id, Long number, String type, Byte size, String fotoAddress, Byte person, Byte bed,
			BigDecimal price) {
		super();
		this.id = id;
		this.number = number;
		this.typeRoom = new TypeRoom(type);
		this.size = size;
		this.fotoAddress = fotoAddress;
		this.person = person;
		this.bed = bed;
		this.price = price;
	}

	public Room(Long id, Long number, String type, Long idType, Byte size, String fotoAddress, Byte person, Byte bed,
			BigDecimal price) {
		super();
		this.id = id;
		this.number = number;
		this.typeRoom = new TypeRoom(idType, type);
		this.size = size;
		this.fotoAddress = fotoAddress;
		this.person = person;
		this.bed = bed;
		this.price = price;
	}

	public Room(Long id, Long number) {
		super();
		this.id = id;
		this.number = number;
	}

}
