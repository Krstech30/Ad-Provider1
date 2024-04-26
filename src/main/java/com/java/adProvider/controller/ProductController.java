package com.java.adProvider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.adProvider.model.Product;
import com.java.adProvider.model.ReferenceDataCategory;
import com.java.adProvider.service.ProductService;
import com.java.adProvider.service.ReferenceDataCategoryService;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private ReferenceDataCategoryService referenceDataCategoryService;

	@GetMapping
	public List<Product> getAllProductList() {
		List<Product> prodlist = productService.getAllProductList();
		return prodlist;

	}

	@PostMapping("/list")
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		ReferenceDataCategory category = new ReferenceDataCategory();

		if (category.getCategory_id() == null) {
			category.setCat_description(product.getReferenceDataCategory().getCat_description());
			category.setShort_description(product.getReferenceDataCategory().getShort_description());
			category = referenceDataCategoryService.createReferenceDataCategory(category);
		}
		product.setReferenceDataCategory(category);
		Product prod = productService.createProduct(product);

		return new ResponseEntity<>(prod, HttpStatus.CREATED);

	}

	@PutMapping("/update/{product_id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long product_id, @RequestBody Product product) {
		ReferenceDataCategory refdatacat = new ReferenceDataCategory();
		if (refdatacat != null) {
			if (refdatacat.getCategory_id() != null) {
				refdatacat.setCat_description(product.getReferenceDataCategory().getCat_description());
				refdatacat.setShort_description(product.getReferenceDataCategory().getShort_description());
				refdatacat = referenceDataCategoryService.createReferenceDataCategory(refdatacat);
			}
			product.setReferenceDataCategory(refdatacat);
		}
		Product updateprod = productService.updateProduct(product_id, product);

		return ResponseEntity.ok(updateprod);
	}

	@PutMapping("/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {

		Product updateprod = productService.updateProducts(product);

		return ResponseEntity.ok(updateprod);
	}

	@DeleteMapping("/{product_id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long product_id) {
		productService.deleteProductById(product_id);
		return ResponseEntity.ok().build();
	}

}
