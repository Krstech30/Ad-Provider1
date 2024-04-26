package com.java.adProvider.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.adProvider.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
