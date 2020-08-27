package com.lti.repository;

import com.lti.entity.Customer;
import com.lti.entity.Vehicle;

public interface BuyInsuranceRepository {
	
	boolean isSameVehicleIsInsuredAlready(String registrationNo);
	
	void submissionOfBuyInsuranceData(Vehicle vehicle);
	
	Customer findById(int id);
}