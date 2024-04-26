package com.java.adProvider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.adProvider.model.Plan;
import com.java.adProvider.repo.PlanRepository;
import com.java.adProvider.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private PlanRepository planRepository;

	@Override
	public Plan createPlan(Plan plan) {
		Plan p = planRepository.save(plan);
		return p;
	}

	@Override
	public Plan getByPlanId(Long plan_id) {
		return planRepository.getById(plan_id);
	}

	@Override
	public Plan updatePlan(Long plan_id, Plan plan) {

		return planRepository.save(plan);
	}

	@Override
	public Plan updatePlan1(Plan plan) {
		return planRepository.save(plan);
	}

	@Override
	public void deletePlanById(Long plan_id) {
		Plan plan=planRepository.findById(plan_id).orElseThrow();
		planRepository.deleteById(plan_id);

	}

	@Override
	public List<Plan> getAllPlanList() {
		List<Plan> plan=planRepository.findAll();
		return plan;
	}

}
