package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	
	@Id
	@GeneratedValue
	private int userid;
	private String username;
	private String password;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public void hashPassword() {
		char[] hashedPass = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToChar(6, password.toCharArray());
		this.password = hashedPass.toString();
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public boolean equals(Object obj) {
		User us = (User)obj;
		return BCrypt.verifyer().verify(us.getPassword().toCharArray(), this.password.toCharArray()).verified && us.getUsername().equals(this.getUsername());
	}
}
