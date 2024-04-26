package com.java.adProvider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.adProvider.model.EntryDetails;
import com.java.adProvider.repo.EntryDetailsRepository;
import com.java.adProvider.repo.PlanRepository;
import com.java.adProvider.repo.ProductRepository;
import com.java.adProvider.service.EntryDetailsService;

@Service
public class EntryDetailsServiceImpl implements EntryDetailsService {
	@Autowired
	private EntryDetailsRepository entryDetailsRepository;

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private ProductRepository productRepository;


	@Override
	public EntryDetails saveEntryDetails(EntryDetails entryDetails1) {

		return entryDetailsRepository.save(entryDetails1);
	}
	@Override
	public List<EntryDetails> findAllWithProductAndPlan() {
		List<EntryDetails> entryDetails = entryDetailsRepository.findAllWithProductAndPlan();
		return entryDetails;
	}
	@Override
	public EntryDetails update(Long entry_id, EntryDetails entryDetails1,
			MultipartFile[] videoFile) {

		return entryDetailsRepository.save(entryDetails1);
	}
	@Override
	public void deleteEntryById(Long entry_id) {
		EntryDetails ed=entryDetailsRepository.findById(entry_id).orElseThrow();

		 entryDetailsRepository.delete(ed);

	}

}
