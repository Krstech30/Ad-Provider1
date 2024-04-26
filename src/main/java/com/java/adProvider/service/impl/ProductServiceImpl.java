package com.java.adProvider.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.adProvider.model.Product;
import com.java.adProvider.repo.ProductRepository;
import com.java.adProvider.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProductList() {
		List<Product> productList = productRepository.findAll();
		return productList;
	}

	@Override
	public Product createProduct(Product product) {

		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Long product_id, Product product) {
		Product existingDetails = productRepository.findById(product_id)
				.orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + product_id));
		existingDetails.setCompany_name(product.getCompany_name());
		existingDetails.setDescription(product.getDescription());
		existingDetails.setName(product.getName());
		existingDetails.setPrice(product.getPrice());
		existingDetails.setStock_quantity(product.getStock_quantity());
		existingDetails.setStatus(false);
		return productRepository.save(existingDetails);

	}

	@Override
	public void deleteProductById(Long product_id) {
		 productRepository.deleteById(product_id);
	}

	@Override
	public Product getProductById(Long productId) {

		return productRepository.getById(productId);
	}

	@Override
	public Product updateProducts(Product product) {

    	return productRepository.save(product);
	}

}
