package com.java.adProvider.service;

import java.util.List;

import com.java.adProvider.model.Plan;

public interface PlanService {

	Plan createPlan(Plan plan);

	Plan getByPlanId(Long plan_id);

	Plan updatePlan(Long plan_id, Plan plan);

	Plan updatePlan1(Plan plan);

	void deletePlanById(Long plan_id);

	List<Plan> getAllPlanList();



}
