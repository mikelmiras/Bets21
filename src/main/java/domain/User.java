package domain;

import java.util.ArrayList;

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
	double money;
	ArrayList<String> movementsArray;
	public User(String username, String password, boolean admin) {
		this.username = username;
		this.password = password;
		this.isAdmin = admin;
		this.money = 0;
		movementsArray = new ArrayList<String>();
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
	public boolean isAdmin() {
		return this.isAdmin;
	}
	public double getMoney() {
		return this.money;
	}
	public ArrayList<String> getMovements(){
		return this.movementsArray;
	}
	public void setMoney(double m) {
		this.money = m;
	}
	@Override
	public boolean equals(Object obj) {
		User us = (User)obj;
		boolean result = BCrypt.verifyer().verify(us.getPassword().toCharArray(), this.password.toCharArray()).verified && us.getUsername().equals(this.getUsername());
		System.out.println("Equals result: " + result);
		return BCrypt.verifyer().verify(us.getPassword().toCharArray(), this.password.toCharArray()).verified && us.getUsername().equals(this.getUsername());
	}
}
