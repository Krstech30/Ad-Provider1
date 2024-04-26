package com.java.adProvider.service;

import java.util.List;

import com.java.adProvider.model.AdProviderDetails;
import com.java.adProvider.model.User;

public interface AdProviderDetailsService {

	List<AdProviderDetails> getAllAdProviderList();

	AdProviderDetails createAdProvider(AdProviderDetails adProviderDetails);

	AdProviderDetails update(Long ad_id, AdProviderDetails adProviderDetails);

	void deleteAdProviderDetailsById(Long ad_id);

	AdProviderDetails getAdProviderDetailsByUser(User user);

	AdProviderDetails getAdProviderDetailsById(Long ad_id);



}
