package com.java.adProvider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.adProvider.model.ReferenceDataCategory;
import com.java.adProvider.service.ReferenceDataCategoryService;

@RestController
@RequestMapping("/referenceDataCategory")
@CrossOrigin("*")
public class ReferenceDataCategoryController {

	@Autowired
	private ReferenceDataCategoryService referenceDataCategoryService;

	@PostMapping
	public ReferenceDataCategory saveReferenceDataCategory(@RequestBody ReferenceDataCategory referenceDataCategory) {
		ReferenceDataCategory refData = referenceDataCategoryService.createReferenceDataCategory(referenceDataCategory);
		return referenceDataCategory;

	}
	@PostMapping("/update/{category_id}")
	public ResponseEntity<ReferenceDataCategory> updateReferenceDataCategory (@PathVariable Long category_id,
			@RequestBody ReferenceDataCategory referenceDataCategory) {
		ReferenceDataCategory updatedReferenceDataCategory  = referenceDataCategoryService.updateReferenceDataCategory(category_id, referenceDataCategory);
		return ResponseEntity.ok(updatedReferenceDataCategory);

	}

}
