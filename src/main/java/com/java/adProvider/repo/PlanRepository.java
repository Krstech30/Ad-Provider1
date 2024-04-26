package com.java.adProvider.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.adProvider.model.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

}
