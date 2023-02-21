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
	private String username;
	private String password;
	boolean isAdmin;
	
	public User(String username, String password, boolean admin) {
		this.username = username;
		this.password = password;
		this.isAdmin = admin;
	}
	public User (String username, String password) {
		this (username, password, false);
	}
	public void hashPassword() {
		char[] hashedPass = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToChar(6, password.toCharArray());
		this.password = "";
		for (char c : hashedPass) {
			this.password += c;
		}
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public boolean equals(Object obj) {
		User us = (User)obj;
		boolean result = BCrypt.verifyer().verify(us.getPassword().toCharArray(), this.password.toCharArray()).verified && us.getUsername().equals(this.getUsername());
		System.out.println("Equals result: " + result);
		return BCrypt.verifyer().verify(us.getPassword().toCharArray(), this.password.toCharArray()).verified && us.getUsername().equals(this.getUsername());
	}
}
