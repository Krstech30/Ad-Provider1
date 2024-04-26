package com.java.adProvider.exceptions;

public class EnterCorrectInformationException extends Exception {
	public EnterCorrectInformationException() {

		super("Please Enter Correct Information");
		// TODO Auto-generated constructor stub
	}

	public EnterCorrectInformationException(String msg) {
		super(msg);
	}
}
