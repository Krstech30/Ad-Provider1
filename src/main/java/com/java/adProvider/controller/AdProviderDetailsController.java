package com.java.adProvider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.adProvider.model.AdProviderDetails;
import com.java.adProvider.model.User;
import com.java.adProvider.repo.UserRepository;
import com.java.adProvider.response.ResponseHandler;
import com.java.adProvider.service.AdProviderDetailsService;
import com.java.adProvider.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/adprovider")
@CrossOrigin("*")
public class AdProviderDetailsController {
	@Autowired
	private AdProviderDetailsService adProviderDetailsService;

	@Autowired
	private UserServiceImpl userServiceimpl;

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public ResponseEntity<?> getAdProviderDetailsList() {
		List<AdProviderDetails> adprovider = adProviderDetailsService.getAllAdProviderList();
		return ResponseHandler.responseBuilder(" Successfully Fetched List Of AdProviderDeatils", HttpStatus.OK.value(),
				adprovider, HttpStatus.OK);
	}

	@PostMapping("/save/{id}")
	public ResponseEntity<?> createAdProvider(@PathVariable Long id, @RequestBody AdProviderDetails adProviderDetails) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		System.out.println("-------------->1" + user);
		adProviderDetails.setUser(user); // System.out.println("-------------->1"+user);
		AdProviderDetails createdadProviderDetails = adProviderDetailsService.createAdProvider(adProviderDetails);
		return new ResponseEntity<>(createdadProviderDetails, HttpStatus.CREATED);
	}

	@GetMapping("/ad_id/{ad_id}")
	public ResponseEntity<?> getAdProviderById(@PathVariable(value = "ad_id") Long ad_id) {
		AdProviderDetails adProviderDetails = adProviderDetailsService.getAdProviderDetailsById(ad_id);
		return ResponseEntity.ok(adProviderDetails);

	}

	@GetMapping("/{id}")
	public ResponseEntity<AdProviderDetails> getAdProviderDetailsByUser(@PathVariable(value = "id") Long id) {
		User user = userRepository.getById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		AdProviderDetails adProviderDetails = adProviderDetailsService.getAdProviderDetailsByUser(user);
		if (adProviderDetails != null) {
			return ResponseEntity.ok(adProviderDetails);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/update/{ad_id}")
	public ResponseEntity<AdProviderDetails> updateAdProviderDetails(@PathVariable Long ad_id,
			@RequestBody AdProviderDetails adProviderDetails) {
		AdProviderDetails updatedAdProviderDetails = adProviderDetailsService.update(ad_id, adProviderDetails);
		return ResponseEntity.ok(updatedAdProviderDetails);
	}

	@DeleteMapping("/delete/{ad_id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long ad_id) {
		adProviderDetailsService.deleteAdProviderDetailsById(ad_id);
		return ResponseEntity.ok().build();
	}

}