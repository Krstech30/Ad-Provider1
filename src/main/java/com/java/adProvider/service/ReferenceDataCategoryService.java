package com.java.adProvider.service;

import com.java.adProvider.model.ReferenceDataCategory;

public interface ReferenceDataCategoryService {

	ReferenceDataCategory createReferenceDataCategory(ReferenceDataCategory referenceDataCategory);

	ReferenceDataCategory updateReferenceDataCategory(Long category_id, ReferenceDataCategory referenceDataCategory);

	ReferenceDataCategory updateReferenceDataCategory1(ReferenceDataCategory referenceDataCat);

}
