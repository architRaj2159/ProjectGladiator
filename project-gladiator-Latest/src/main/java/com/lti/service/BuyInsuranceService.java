package com.lti.service;

import org.springframework.stereotype.Service;

import com.lti.entity.Vehicle;
import com.lti.exception.BuyInsuranceServiceException;

public interface BuyInsuranceService {

	void submissionOfInsuranceDetails(Vehicle vehicle) throws BuyInsuranceServiceException;

}