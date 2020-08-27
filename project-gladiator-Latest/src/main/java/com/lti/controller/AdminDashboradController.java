package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Claim;
import com.lti.exception.InsuranceServiceException;
import com.lti.service.ClaimInsuranceService;
import com.lti.service.DashboardService;

@RestController
@CrossOrigin
public class AdminDashboradController {
	
	@Autowired
	private DashboardService dashboardService;
	
	
	@GetMapping(path = "/getByClaimStatus/{processingStatus}", produces = "application/json")
	public List<Claim> fetchClaimByStatus(@PathVariable(value = "processingStatus") String processingStatus){
		return dashboardService.fetchByProcessingStatusService(processingStatus);
	}
	

	@GetMapping(path = "/getCustomerCount", produces = "application/json")
	public long getCustomerCountController() {
		return dashboardService.getCountCustomerService();
	}
	
	
	@GetMapping(path = "/getClaimCount", produces = "application/json")
	public long getClaimCountController() {
		return dashboardService.getCountClaimService();
	}
	
	
	@GetMapping(path = "/getPolicyCount", produces = "application/json")
	public long getPolicyCountController() {
		return dashboardService.getCountPolicyService();
	}
	

	@GetMapping(path = "/getRenewCount", produces = "application/json")
	public long getRenewCountController() {
		return dashboardService.getCountRenewService();
	}

}
