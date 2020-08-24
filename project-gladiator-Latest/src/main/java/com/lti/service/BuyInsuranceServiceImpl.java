package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Vehicle;
import com.lti.exception.BuyInsuranceServiceException;
import com.lti.repository.BuyInsuranceRepository;

@Service
public class BuyInsuranceServiceImpl implements BuyInsuranceService {
	@Autowired
	private BuyInsuranceRepository buyInsuranceRepo;
	
	@Override
	public void submissionOfInsuranceDetails(Vehicle vehicle) throws BuyInsuranceServiceException {
		if(!buyInsuranceRepo.isSameVehicleIsInsuredAlready(vehicle.getRegistrationNo())) {
			buyInsuranceRepo.submitData(vehicle);
		}else {
			throw new BuyInsuranceServiceException("This Vehicle is already Insured, you have to renew the insurance");
		}
	}
}
