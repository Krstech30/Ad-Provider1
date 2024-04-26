package com.java.adProvider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.adProvider.model.ReferenceData;
import com.java.adProvider.repo.ReferenceDataRepository;
import com.java.adProvider.service.ReferenceDataService;

@Service
public class ReferenceDataServiceImpl implements ReferenceDataService {
	@Autowired
	private ReferenceDataRepository referenceDataRepository;

	@Override
	public ReferenceData createData(ReferenceData referenceData) {

		return referenceDataRepository.save(referenceData);
	}

	@Override
	public List<ReferenceData> getAll() {
		List<ReferenceData> rd = referenceDataRepository.findAll();
		return rd;
	}

	@Override
	public List<ReferenceData> getReferenceData() {
		// TODO Auto-generated method stub
		return referenceDataRepository.fetchCategory();
	}

}
