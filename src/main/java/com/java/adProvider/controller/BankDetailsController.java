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

import com.java.adProvider.model.BankDetails;
import com.java.adProvider.model.User;
import com.java.adProvider.repo.UserRepository;
import com.java.adProvider.response.ResponseHandler;
import com.java.adProvider.service.BankDetailsService;

@RestController
@RequestMapping("/bank")
@CrossOrigin("*")
public class BankDetailsController {

	@Autowired
	private BankDetailsService bankDetailsService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping()
	public ResponseEntity<?> getBankDetailsList() {
		List<BankDetails> bankDetails = bankDetailsService.getAllBankDetailsList();
		return ResponseHandler.responseBuilder(" Successfully Fetched List Of AdProviderDeatils", HttpStatus.OK.value(),
				bankDetails, HttpStatus.OK);
	}

	@PostMapping("/bankdetails/{id}")
	public ResponseEntity<BankDetails> createBankDetails(@PathVariable Long id, @RequestBody BankDetails bankDetails) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		bankDetails.setUser(user); // Set the user for BankDetails
		BankDetails createdBankDetails = bankDetailsService.createBankDetails(bankDetails);
		return new ResponseEntity<>(createdBankDetails, HttpStatus.CREATED);
	}

	@GetMapping("/bank_id/{bank_id}")
	public ResponseEntity<?> getBankDetailsById(@PathVariable(value = "bank_id") Long bank_id) {
		BankDetails bankDetails = bankDetailsService.getBankDetailsById(bank_id);
		return ResponseEntity.ok(bankDetails);

	}

	@GetMapping("/{id}")
	public ResponseEntity<BankDetails> getBankDetailsByUserId(@PathVariable(value = "id") Long id) {
		User user = userRepository.getById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		BankDetails bankDetails = bankDetailsService.getBankDetailsByUser(user);
		if (bankDetails != null) {
			return ResponseEntity.ok(bankDetails);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("update/{bank_id}")
	public ResponseEntity<BankDetails> updateBankDetails(@PathVariable Long bank_id,
			@RequestBody BankDetails bankDetails) {
		BankDetails updatedBankDetails = bankDetailsService.update(bank_id, bankDetails);
		return ResponseEntity.ok(updatedBankDetails);

	}

	@DeleteMapping("/delete/{bank_id}")
	public ResponseEntity<?> deleteBankDetails(@PathVariable Long bank_id) {
		bankDetailsService.BankDetailsDeleteById(bank_id);
		return ResponseEntity.ok().build();
	}

}
