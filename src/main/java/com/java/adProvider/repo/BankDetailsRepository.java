package com.java.adProvider.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.adProvider.model.BankDetails;
import com.java.adProvider.model.User;

public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {
	@Query(value = "SELECT b FROM BankDetails b WHERE b.user = :user")
	BankDetails getByUser(@Param("user") User user);

	@Query(value = "SELECT * FROM tb_user_bank_details  WHERE bank_id=?", nativeQuery = true)
	BankDetails findByBankId(Long bank_id);
}
