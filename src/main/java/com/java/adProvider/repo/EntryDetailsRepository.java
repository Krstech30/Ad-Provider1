package com.java.adProvider.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.adProvider.model.EntryDetails;

@Repository
public interface EntryDetailsRepository extends JpaRepository<EntryDetails, Long> {
	@Query(value = "SELECT ed.* FROM entry_details ed " +
            "JOIN tb_product p ON ed.product_id = p.product_id " +
            "JOIN tb_plan pl ON ed.plan_id = pl.plan_id", nativeQuery = true)
	List<EntryDetails> findAllWithProductAndPlan();

}
