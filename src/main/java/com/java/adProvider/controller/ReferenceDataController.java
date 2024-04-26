package com.java.adProvider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.adProvider.model.ReferenceData;
import com.java.adProvider.model.ReferenceDataCategory;
import com.java.adProvider.repo.ReferenceDataRepository;
import com.java.adProvider.service.ReferenceDataCategoryService;
import com.java.adProvider.service.ReferenceDataService;

@RestController
@RequestMapping("/referenceData")
@CrossOrigin("*")
public class ReferenceDataController {
	@Autowired
	private ReferenceDataService referenceDataService;

	@Autowired
	private ReferenceDataRepository referenceDataRepository;

	@Autowired
	private ReferenceDataCategoryService referenceDataCategoryService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<ReferenceData> rdata = referenceDataService.getAll();
		return new ResponseEntity<>(rdata, HttpStatus.CREATED);

	}

	@PostMapping("/save")
	public ResponseEntity<?> saveReferenceData(@RequestBody ReferenceData referenceData) {
		ReferenceDataCategory category = referenceData.getReferenceDataCategory();
		if (category == null) {

			return new ResponseEntity<>("ReferenceDataCategory is null", HttpStatus.BAD_REQUEST);
		}

		if (category.getCategory_id() == null) {
			category.setCat_description(referenceData.getReferenceDataCategory().getCat_description());
			category = referenceDataCategoryService.createReferenceDataCategory(category);
		}
		referenceData.setReferenceDataCategory(category);
		ReferenceData createReferenceData = referenceDataService.createData(referenceData);
		return new ResponseEntity<>(createReferenceData, HttpStatus.CREATED);
	}

	@GetMapping("/list")
	public List<ReferenceData> getReferenceData() {
		return referenceDataService.getReferenceData();
	}
}
