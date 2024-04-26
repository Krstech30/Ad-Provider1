package com.java.adProvider.exceptions;

public class UserNotFoundException extends Exception {
	public UserNotFoundException() {

		super("User with this name is not there in DB!!!");
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String msg) {
		super(msg);
	}
}
