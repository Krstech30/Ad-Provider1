package com.java.adProvider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.adProvider.model.UserAdsHistoryDetails;
import com.java.adProvider.repo.UserAdsHistoryDetailsRepository;
import com.java.adProvider.service.UserAdsHistoryDetailsService;

@Service
public class UserAdsHistoryDetailsServiceImpl implements UserAdsHistoryDetailsService {
	@Autowired
	private UserAdsHistoryDetailsRepository userAdsHistoryDetailsRepository;

	@Override
	public UserAdsHistoryDetails createuserAdsHistoryDetails(UserAdsHistoryDetails userAdsHistoryDetails) {

		return userAdsHistoryDetailsRepository.save(userAdsHistoryDetails);
	}

}
