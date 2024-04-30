package com.java.adProvider.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponse {

	private String token;
	private String refreshToken;
	private Long id;
	private String username;
	private String message;
	private String password;
	private String email;

}