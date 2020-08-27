package com.lti.service;

import java.util.List;

import com.lti.entity.Claim;
import com.lti.entity.Customer;
import com.lti.entity.Vehicle;
import com.lti.exception.InsuranceServiceException;

public interface BuyInsuranceService {

	void submissionOfBuyInsuranceDetails(Vehicle vehicle) throws InsuranceServiceException;
	Customer findById(int id);
}