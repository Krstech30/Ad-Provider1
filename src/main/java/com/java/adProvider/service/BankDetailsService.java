package com.java.adProvider.service;

import java.util.List;

import com.java.adProvider.model.BankDetails;
import com.java.adProvider.model.User;

public interface BankDetailsService {

	BankDetails createBankDetails(BankDetails bankDetails);

	BankDetails update(Long bank_id, BankDetails bankDetails);

	BankDetails getBankDetailsByUser(User user);

	List<BankDetails> getAllBankDetailsList();

	BankDetails getBankDetailsById(Long bank_id);

	void BankDetailsDeleteById(Long bank_id);

}
