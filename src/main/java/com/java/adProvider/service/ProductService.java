package com.java.adProvider.service;

import java.util.List;

import com.java.adProvider.model.Product;

public interface ProductService {

	List<Product> getAllProductList();

	Product createProduct(Product product);

	Product updateProduct(Long product_id, Product product);

	void deleteProductById(Long product_id);

	Product getProductById(Long productId);

	Product updateProducts(Product product);



}
