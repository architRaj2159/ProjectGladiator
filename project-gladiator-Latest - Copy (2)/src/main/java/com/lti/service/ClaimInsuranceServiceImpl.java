package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Claim;
import com.lti.entity.Customer;
import com.lti.entity.Policy;
import com.lti.entity.Vehicle;
import com.lti.exception.InsuranceServiceException;
import com.lti.repository.BuyInsuranceRepository;
import com.lti.repository.ClaimInsuranceRepository;

@Service
public class ClaimInsuranceServiceImpl implements ClaimInsuranceService {
	@Autowired
	private ClaimInsuranceRepository claimInsuranceRepo;

	@Override
	public void saveAndUpdateOfClaimInsuranceDetails(Claim claim) throws InsuranceServiceException {
		claimInsuranceRepo.submitAndUpdatingOfClaimInsurance(claim);
	}

	@Override
	public List<Claim> getClaimInsuranceData() throws InsuranceServiceException {
		List<Claim> list = claimInsuranceRepo.fetchAllClaimData();
		return list;
	}

	@Override
	public Claim getClaimDataById(int id) {
		Claim claim = claimInsuranceRepo.fetchDataByIdForClaimInsurance(id);
		return claim;
	}

	@Override
	public Customer findByCustomerId(int id) {
		return claimInsuranceRepo.findByCustomerId(id);
	}

	@Override
	public Policy findByPolicyId(int id) {
		return claimInsuranceRepo.findByPolicyId(id);
	}
}
