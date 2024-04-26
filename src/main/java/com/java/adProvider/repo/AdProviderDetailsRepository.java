package com.java.adProvider.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.adProvider.model.AdProviderDetails;
import com.java.adProvider.model.User;

@Repository
public interface AdProviderDetailsRepository extends JpaRepository<AdProviderDetails, Long> {

	@Query("SELECT apd FROM AdProviderDetails apd WHERE apd.user = :user")
	AdProviderDetails findAdProviderDetailsByUser(@Param("user") User user);

	@Query(value = "SELECT * FROM tb_ads_provider_details  WHERE ad_id=?", nativeQuery = true)
	AdProviderDetails findByAdProviderId(Long ad_id);


}