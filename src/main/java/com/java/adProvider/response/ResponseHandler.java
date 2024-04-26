package com.java.adProvider.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<?> responseBuilder(String message, int value, Object user, HttpStatus httpStatus) {
		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("status", value);
		response.put("data", user);
		response.put("HttpStatus", httpStatus);
		return new ResponseEntity<>(response, httpStatus);
	}

}