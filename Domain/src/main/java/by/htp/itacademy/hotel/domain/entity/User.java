package by.htp.itacademy.hotel.domain.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The object of this class stores information about the user. It is possible to
 * serialize an object of this class.
 * 
 * @author Viktor
 *
 */
@Data
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = -3115010418744396347L;

	private Long id;
	private String login;
	private Long hashCodePass;
	private String password;
	private String name;
	private String surname;
	private String email;
	private Boolean role;

	public User(String login) {
		this.login = login;
	}

	public User(Long id) {
		this.id = id;
	}

	public User(String login, Long hashLong) {
		this.login = login;
		this.hashCodePass = hashLong;
	}

	public User(String login, Long hashPassUser, String name, String surname, String email) {
		this.login = login;
		this.hashCodePass = hashPassUser;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
}
