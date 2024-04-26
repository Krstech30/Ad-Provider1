package com.java.adProvider.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.java.adProvider.model.EntryDetails;

public interface EntryDetailsService {

	EntryDetails saveEntryDetails(EntryDetails entryDetails1);

	List<EntryDetails> findAllWithProductAndPlan();

	EntryDetails update(Long entry_id, EntryDetails entryDetails1,
			MultipartFile[] videoFile);

	void deleteEntryById(Long entry_id);

}
