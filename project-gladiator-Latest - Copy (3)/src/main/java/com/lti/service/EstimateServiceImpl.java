package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Premium;
//import com.lti.exception.EstimateServiceException;
import com.lti.repository.EstimateRepository;

@Service
public class EstimateServiceImpl implements EstimateService {
	
	@Autowired
	private EstimateRepository estimateRepository;
	
	//@Autowired
	//private Premium newPremium;
	
	@Override
	public double estimateClaim(Premium premium) {

		double claimAmt = estimateRepository.estimateClaimAmount(premium);
		
		System.out.println("in service");
		System.out.println("in service try");
		return claimAmt;
	}
	
	public void saveData(Premium premium) {
		Premium newPremium = new Premium();
		
		newPremium.setId(premium.getId());
		newPremium.setAge(premium.getAge());
		newPremium.setAmount(premium.getAmount());
		newPremium.setDepreciationRate(premium.getDepreciationRate());
		newPremium.setModel(premium.getModel());
		newPremium.setType(premium.getType());
		newPremium.setDuration(premium.getDuration());
		newPremium.setLossSuffered(premium.getLossSuffered());
		newPremium.setTotalCostOfVehicle(premium.getTotalCostOfVehicle());
		estimateRepository.save(newPremium);
		estimateClaim(newPremium);
	}
}
