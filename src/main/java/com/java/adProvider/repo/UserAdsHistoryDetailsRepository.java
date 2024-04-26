package com.java.adProvider.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.adProvider.model.UserAdsHistoryDetails;

@Repository
public interface UserAdsHistoryDetailsRepository extends JpaRepository<UserAdsHistoryDetails, Long> {

}
