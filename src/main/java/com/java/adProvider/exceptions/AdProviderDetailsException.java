package com.java.adProvider.exceptions;

public class AdProviderDetailsException extends Exception {
	public AdProviderDetailsException() {
		super("AdProvider is already there in DB!!! please try.....");

	}

	public AdProviderDetailsException(String msg) {
		super(msg);
	}
}
