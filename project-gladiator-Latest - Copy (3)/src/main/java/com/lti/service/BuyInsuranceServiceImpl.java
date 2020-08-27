package com.lti.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Customer;
import com.lti.entity.Vehicle;
import com.lti.exception.InsuranceServiceException;
import com.lti.repository.BuyInsuranceRepository;

@Service
public class BuyInsuranceServiceImpl implements BuyInsuranceService {
	@Autowired
	private BuyInsuranceRepository buyInsuranceRepo;
	
	@Override
	public void submissionOfBuyInsuranceDetails(Vehicle vehicle) throws InsuranceServiceException {
		if(!buyInsuranceRepo.isSameVehicleIsInsuredAlready(vehicle.getRegistrationNo())) {
			buyInsuranceRepo.submissionOfBuyInsuranceData(vehicle);
		}else {
			throw new InsuranceServiceException("This Vehicle is already Insured, you have to renew the insurance");
		}
	}

	@Override
	public Customer findById(int id) {
		return buyInsuranceRepo.findById(id);
}
	
}
