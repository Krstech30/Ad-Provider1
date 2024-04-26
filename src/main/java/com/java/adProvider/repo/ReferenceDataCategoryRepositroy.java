package com.java.adProvider.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.adProvider.model.ReferenceDataCategory;

@Repository
public interface ReferenceDataCategoryRepositroy extends JpaRepository<ReferenceDataCategory, Long> {
//	@Query(value = "UPDATE  tb_reference_data_category set  WHERE bank_id=?", nativeQuery = true)
//     ReferenceDataCategory updateCatById(Long category_id);



}
