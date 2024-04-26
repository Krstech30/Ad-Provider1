package com.java.adProvider.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.adProvider.model.AdProviderDetails;
import com.java.adProvider.model.User;
import com.java.adProvider.repo.AdProviderDetailsRepository;
import com.java.adProvider.repo.UserRepository;
import com.java.adProvider.service.AdProviderDetailsService;

@Service
public class AdProviderDetailsServiceImpl implements AdProviderDetailsService {
	@Autowired
	private AdProviderDetailsRepository adProviderDetailsRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<AdProviderDetails> getAllAdProviderList() {
		List<AdProviderDetails> adprovider = adProviderDetailsRepository.findAll();
		return adprovider;
	}

	@Override
	public AdProviderDetails createAdProvider(AdProviderDetails adProviderDetails) {

		AdProviderDetails savedAdProvider = adProviderDetailsRepository.save(adProviderDetails);

		return savedAdProvider;
	}

	@Override
	public AdProviderDetails update(Long ad_id, AdProviderDetails adProviderDetails) {
		AdProviderDetails existingUser = adProviderDetailsRepository.findById(ad_id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + ad_id));
		existingUser.setAdhar_card_no(adProviderDetails.getAdhar_card_no());
		existingUser.setPan_card_no(adProviderDetails.getPan_card_no());
		existingUser.setAddress_line_1(adProviderDetails.getAddress_line_1());
		existingUser.setAddress_line_2(adProviderDetails.getAddress_line_2());
		existingUser.setEducation(adProviderDetails.getEducation());
		existingUser.setCompany_name(adProviderDetails.getCompany_name());
		existingUser.setCompany_add(adProviderDetails.getCompany_add());
		existingUser.setServices(adProviderDetails.getServices());
		existingUser.setProvided_by(adProviderDetails.getProvided_by());
		existingUser.setProduct_name(adProviderDetails.getProduct_name());
//        existingUser.setPhone(adProviderDetails.getPhone());
		return adProviderDetailsRepository.save(existingUser);
	}

	@Override
	public void deleteAdProviderDetailsById(Long ad_id) {
	 AdProviderDetails ad=	adProviderDetailsRepository.findById(ad_id).orElseThrow();
	 adProviderDetailsRepository.deleteById(ad_id);
	}

	@Override
	public AdProviderDetails getAdProviderDetailsByUser(User user) {
		return adProviderDetailsRepository.findAdProviderDetailsByUser(user);
	}

	@Override
	public AdProviderDetails getAdProviderDetailsById(Long ad_id) {
		return adProviderDetailsRepository.findByAdProviderId(ad_id);
	}



}
