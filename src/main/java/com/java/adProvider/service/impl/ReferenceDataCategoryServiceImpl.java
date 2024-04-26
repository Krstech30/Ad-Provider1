package com.java.adProvider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.adProvider.model.ReferenceDataCategory;
import com.java.adProvider.repo.ReferenceDataCategoryRepositroy;
import com.java.adProvider.service.ReferenceDataCategoryService;

@Service
public class ReferenceDataCategoryServiceImpl implements ReferenceDataCategoryService {

	@Autowired
	private ReferenceDataCategoryRepositroy referenceDataCategoryRepositroy;

	@Override
	public ReferenceDataCategory createReferenceDataCategory(ReferenceDataCategory referenceDataCategory) {
		ReferenceDataCategory refData = referenceDataCategoryRepositroy.save(referenceDataCategory);
		return refData;
	}

	@Override
	public ReferenceDataCategory updateReferenceDataCategory(Long category_id,
			ReferenceDataCategory referenceDataCategory) {
		ReferenceDataCategory existingDetails = referenceDataCategoryRepositroy.findById(category_id).orElseThrow();

		existingDetails.setCat_description(referenceDataCategory.getCat_description());
		existingDetails.setShort_description(referenceDataCategory.getShort_description());

		return referenceDataCategoryRepositroy.save(existingDetails);


	}

	@Override
	public ReferenceDataCategory updateReferenceDataCategory1(ReferenceDataCategory referenceDataCat) {
		// TODO Auto-generated method stub
		return referenceDataCategoryRepositroy.save(referenceDataCat);
	}



}
