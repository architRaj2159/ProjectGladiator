package com.lti.service;

import java.util.List;

import com.lti.entity.Claim;
import com.lti.entity.Customer;
import com.lti.entity.Policy;
import com.lti.exception.InsuranceServiceException;

public interface ClaimInsuranceService {

	void saveAndUpdateOfClaimInsuranceDetails(Claim claim) throws InsuranceServiceException;

	List<Claim> getClaimInsuranceData() throws InsuranceServiceException;

	Claim getClaimDataById(int id);

	Customer findByCustomerId(int id);
	
	Policy findByPolicyId(int id);

}