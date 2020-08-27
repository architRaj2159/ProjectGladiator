package com.lti.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Claim;
import com.lti.entity.Customer;
import com.lti.entity.Policy;

public interface ClaimInsuranceRepository {

	void submitAndUpdatingOfClaimInsurance(Claim claim);

	Claim fetchDataByIdForClaimInsurance(int id);

	List<Claim> fetchAllClaimData();

	Customer findByCustomerId(int id);
	
	Policy findByPolicyId(int id);

}