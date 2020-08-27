package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Premium;

@Repository
public class EstimateRepositoryImpl implements EstimateRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void save(Premium premium) {
		//System.out.println("in save");
		entityManager.merge(premium);
	}
	
	@Override
	public double estimateClaimAmount(Premium premium) {
		double estimatedAmount=0.0;
		//formula = ((loss_suffered*amount)/total_cost_of_vehicle)*age*depreciation_rate;
		estimatedAmount = ((premium.getLossSuffered() * 3000) / premium.getTotalCostOfVehicle())
			* premium.getAge() * (premium.getDepreciationRate());
		return estimatedAmount;
	}
	
}
