package com.java.adProvider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.adProvider.model.Login;
import com.java.adProvider.model.User;
import com.java.adProvider.repo.RoleRepository;
import com.java.adProvider.repo.UserRepository;
import com.java.adProvider.response.LoginResponse;
import com.java.adProvider.service.UserService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<User> getUser() {
		List<User> user = null;
		return userRepository.findAll();
	}

	@Override
	public User findByPhone(String phone) {
		User user = userRepository.findByPhone(phone);
		return user;
	}

	@Override
	public User findByUserId(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user id not found" + id));
	}

	@Override
	public User createUser(User user) throws Exception {

		return userRepository.save(user);

	}

	@Override
	public User verifyUser(Login loginRequest) {
		LoginResponse loginResponse = new LoginResponse();
		User user = userRepository.findByUsername(loginRequest.getUsername());
		String currentpassword = loginRequest.getPassword();
		String storedpassword = user.getPassword();
		if (user != null && currentpassword.equals(storedpassword)) {
//      		user.setVerified(true);

			this.userRepository.save(user);
			loginResponse.setMessage("User Verified Successfully");

		} else {
			loginResponse.setMessage("user not Verfied Successfully");

		}
		loginResponse.setUsername(loginRequest.getUsername());
		loginResponse.setPassword(loginRequest.getPassword());
		return user;

	}

	@Override
	public User updateImage(Long id, User newuser, MultipartFile[] imageFile) {

		return userRepository.save(newuser);
	}

}
