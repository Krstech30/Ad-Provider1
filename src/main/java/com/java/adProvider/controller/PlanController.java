package com.java.adProvider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.java.adProvider.model.Plan;
import com.java.adProvider.service.PlanService;

@RestController
@RequestMapping("/plan")
@CrossOrigin("*")
public class PlanController {
	@Autowired
	private PlanService planService;

	@GetMapping
	public List<Plan> getAllPlanList() {
		List<Plan> planlist = planService.getAllPlanList();
		return planlist;

	}

	@PostMapping("/list")
	public Plan savePlan(@RequestBody Plan plan) {
		Plan p = planService.createPlan(plan);
		return plan;

	}

	@PutMapping("/update/{plan_id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long plan_id, @RequestBody Plan plan) {
		Plan updateplan = planService.updatePlan(plan_id, plan);
		return ResponseEntity.ok(updateplan);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody Plan plan) {
		Plan updateplan = planService.updatePlan1(plan);
		return ResponseEntity.ok(updateplan);
	}
	@DeleteMapping("/delete/{plan_id}")
	public ResponseEntity<?> deletePlan(@PathVariable Long plan_id) {
		planService.deletePlanById(plan_id);
		return ResponseEntity.ok().build();
	}
}
