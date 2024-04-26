package com.java.adProvider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.adProvider.model.User;
import com.java.adProvider.model.UserAdsHistoryDetails;
import com.java.adProvider.repo.UserRepository;
import com.java.adProvider.service.UserAdsHistoryDetailsService;

@RestController
@RequestMapping("/useradshistory")
@CrossOrigin("*")
public class UserAdsHistoryDetailsController {
	@Autowired
	private UserAdsHistoryDetailsService userAdsHistoryDetailsService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/useradshistoryDetails/{id}")
	public ResponseEntity<?> saveUserAdsHistoryDetails(@PathVariable Long id,
			@RequestBody UserAdsHistoryDetails userAdsHistoryDetails) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		userAdsHistoryDetails.setUser(user); // Set the user for BankDetails
		UserAdsHistoryDetails createuserAdsHistoryDetails = userAdsHistoryDetailsService
				.createuserAdsHistoryDetails(userAdsHistoryDetails);
		return new ResponseEntity<>(createuserAdsHistoryDetails, HttpStatus.CREATED);

	}
}
