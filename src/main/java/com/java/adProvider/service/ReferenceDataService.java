package com.java.adProvider.service;

import java.util.List;

import com.java.adProvider.model.ReferenceData;

public interface ReferenceDataService {

	ReferenceData createData(ReferenceData referenceData);

	List<ReferenceData> getAll();

	List<ReferenceData> getReferenceData();

}
