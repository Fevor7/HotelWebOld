package by.htp.itacademy.hotel.domain.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fields of this class contain description of the hotel
 * 
 * @author viktor
 *
 */
@Data
@NoArgsConstructor
public class Hotel implements Serializable {

	private static final long serialVersionUID = 3911096926880502785L;

	private String name;
	private String address;
	private String location;
	private String about;
	private String starReting;
	private List<FacilitiesHotel> facilities;
	private List<FotoAddressHotel> fotoAddress;

	public Hotel(String name, String address, String location, String adout, String starReting) {
		super();
		this.name = name;
		this.address = address;
		this.location = location;
		this.about = adout;
		this.starReting = starReting;
	}

}
