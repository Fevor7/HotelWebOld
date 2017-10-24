package by.htp.itacademy.hotel.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The object of this class will store customer requests.
 * 
 * @author Viktor
 *
 */
@Data
@NoArgsConstructor
public class Order implements Serializable {

	private static final long serialVersionUID = 3809323129288556367L;

	private Long orderId;
	private Room room;
	private User user;
	private Date dateStart;
	private Date dateEnd;
	private Byte bedNumber;
	private Byte personNumber;
	private TypeRoom typeRoom;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private BigDecimal totalAmount;
	private OrderStatus orderStatus;
	private Integer pageNumber;

	public Order(String status) {
		this.orderStatus = new OrderStatus(status);
	}

	public Order(Long orderId) {
		this.orderId = orderId;
	}

	public Order(Long orderId, String status) {
		this.orderId = orderId;
		this.orderStatus = new OrderStatus(status);
	}

	public Order(Long idStatus, Integer pageNumber) {
		this.orderStatus = new OrderStatus(idStatus);
		this.pageNumber = pageNumber;
	}

	public Order(Date dateStart, Date dateEnd, Byte bedNumber, Byte personNumber, Long idTypeRoom, Long userId,
			Long idStatus) {
		super();
		this.user = new User(userId);
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.bedNumber = bedNumber;
		this.personNumber = personNumber;
		this.typeRoom = new TypeRoom(idTypeRoom);
		this.orderStatus = new OrderStatus(idStatus);
	}

	public Order(Date dateStart, Date dateEnd, Byte bedNumber, Byte personNumber, BigDecimal minPrice,
			BigDecimal maxPrice, Long idTypeRoom) {
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.bedNumber = bedNumber;
		this.personNumber = personNumber;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.typeRoom = new TypeRoom(idTypeRoom);
	}

	public Order(Long id, Date dateStart, Date dateEnd, Byte bedNumber, Long idTypeRoom, Byte personNumber) {
		this.orderId = id;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.bedNumber = bedNumber;
		this.personNumber = personNumber;
		this.typeRoom = new TypeRoom(idTypeRoom);
	}

	public Order(Long id, Long numberRoom, Date dateStrart, Date dateEnd, Byte bedNumber, Byte personNumber,
			Long idTypeRoom, BigDecimal totalAmount, Long idStatus) {
		this.orderId = id;
		this.room = new Room(null, numberRoom);
		this.dateStart = dateStrart;
		this.dateEnd = dateEnd;
		this.bedNumber = bedNumber;
		this.personNumber = personNumber;
		this.typeRoom = new TypeRoom(idTypeRoom);
		this.totalAmount = totalAmount;
		this.orderStatus = new OrderStatus(idStatus);
	}

	public Order(Long id, Long numberRoom, String userLogin, Date dateStrart, Date dateEnd, Byte bedNumber,
			Byte personNumber, String typeRoom, Long idTypeRoom, BigDecimal totalAmount, String status, Long idStatus) {
		this.orderId = id;
		this.room = new Room(null, numberRoom);
		this.user = new User(userLogin);
		this.dateStart = dateStrart;
		this.dateEnd = dateEnd;
		this.bedNumber = bedNumber;
		this.personNumber = personNumber;
		this.typeRoom = new TypeRoom(idTypeRoom, typeRoom);
		this.totalAmount = totalAmount;
		this.orderStatus = new OrderStatus(idStatus, status);
	}

	public Order(Long orderId, Long roomId, Date dateStart, Date dateEnd, Byte bedNumber, Byte personNumber,
			Long idTypeRoom, Long idStatus, BigDecimal totalAmount) {
		this.orderId = orderId;
		this.room = new Room(roomId, null);
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.bedNumber = bedNumber;
		this.personNumber = personNumber;
		this.typeRoom = new TypeRoom(idTypeRoom);
		this.orderStatus = new OrderStatus(idStatus);
		this.totalAmount = totalAmount;
	}

	public Order(Date dateStart, Date dateEnd, Byte bedNumber, Byte personNumber, Long idTypeRoom, Integer pageNumber) {
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.bedNumber = bedNumber;
		this.personNumber = personNumber;
		this.typeRoom = new TypeRoom(idTypeRoom);
		this.pageNumber = pageNumber;
	}

}
