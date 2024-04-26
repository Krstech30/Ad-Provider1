package com.java.adProvider.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.adProvider.model.BankDetails;
import com.java.adProvider.model.User;
import com.java.adProvider.repo.BankDetailsRepository;
import com.java.adProvider.service.BankDetailsService;

@Service
public class BankDetailsServiceImpl implements BankDetailsService {
	@Autowired
	private BankDetailsRepository bankDetailsRepository;

	@Override
	public List<BankDetails> getAllBankDetailsList() {
		List<BankDetails> bank = bankDetailsRepository.findAll();
		return bank;
	}

	@Override
	public BankDetails createBankDetails(BankDetails bankDetails) {
		BankDetails bankDetails1 = bankDetailsRepository.save(bankDetails);
		return bankDetails1;
	}

	@Override
	public BankDetails update(Long bank_id, BankDetails bankDetails) {
		BankDetails existingDetails = bankDetailsRepository.findById(bank_id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + bank_id));
		existingDetails.setBank_name(bankDetails.getBank_name());
		existingDetails.setBranch_name(bankDetails.getBranch_name());
		existingDetails.setAccount_holder_name(bankDetails.getAccount_holder_name());
		existingDetails.setAccount_number(bankDetails.getAccount_number());
		existingDetails.setIfsc_code(bankDetails.getIfsc_code());
		existingDetails.setUpload_date(bankDetails.getUpload_date());
		return bankDetailsRepository.save(existingDetails);

	}

	@Override
	public BankDetails getBankDetailsByUser(User user) {

		return bankDetailsRepository.getByUser(user);
	}

	@Override
	public void BankDetailsDeleteById(Long bank_id) {
		BankDetails bank=bankDetailsRepository.findById(bank_id).orElseThrow();
		bankDetailsRepository.deleteById(bank_id);

	}

	@Override
	public BankDetails getBankDetailsById(Long bank_id) {
		return bankDetailsRepository.findByBankId(bank_id);
	}




}
