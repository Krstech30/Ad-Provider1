package com.java.adProvider.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.java.adProvider.model.Login;
import com.java.adProvider.model.User;

public interface UserService {

	List<User> getUser();

	User findByUserId(Long id);

	User createUser(User user) throws Exception;

	User findByPhone(String phone);

	User verifyUser(Login loginRequest);

	User updateImage(Long id, User newuser, MultipartFile[] imageFile);

}
