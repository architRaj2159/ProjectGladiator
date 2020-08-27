package com.lti.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Policy;
import com.lti.exception.RenewServiceException;
import com.lti.repository.RenewRepository;

@Service
public class RenewServiceImpl implements RenewService {
	
	@Autowired
	private RenewRepository renewRepository;
	
	@Override
	public String renewIns(Policy policy) {
	  if(renewRepository.isPolicyPresent(policy.getId())) {
		if(renewRepository.isPolicyExpired(policy.getId())) {
			//System.out.println("in service");
			Policy existingPolicy = renewRepository.findByPolicyId(policy.getId());
			//System.out.println(existingPolicy.getStatus());
			existingPolicy.setStatus("Renewed");
			existingPolicy.setIssueDate(LocalDate.now());
			existingPolicy.setDuration(policy.getDuration());
			existingPolicy.setExpiryDate(LocalDate.of(LocalDate.now().getYear()+(policy.getDuration()),
					LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()));
			renewRepository.save(existingPolicy);
			//Policy p = renewRepository.findByPolicyId(policy.getId());
			return "success";
		}
		else 
			throw new RenewServiceException("Policy has not expired yet. Cannot be renewed");
	}
	  else throw new RenewServiceException("Policy does not exist.");
}
}
