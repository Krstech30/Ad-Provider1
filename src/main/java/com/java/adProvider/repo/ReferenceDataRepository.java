package com.java.adProvider.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.adProvider.model.ReferenceData;

public interface ReferenceDataRepository extends JpaRepository<ReferenceData, Long> {
	@Query(value = "select r.ref_data_id,r.description,r.secondary_code,c.category_id,c.description,c.short_description from tab_reference_data1 r inner JOIN tb_reference_data_category c on c.category_id=r.category_id", nativeQuery = true)
	List<ReferenceData> fetchCategory();

}
