package com.java.adProvider.exceptions;

public class UserFoundException extends Exception {
	public UserFoundException() {

		super("User with this name is already there in DB!!! please try with another username");
		// TODO Auto-generated constructor stub
	}

	public UserFoundException(String msg) {
		super(msg);
	}
}
