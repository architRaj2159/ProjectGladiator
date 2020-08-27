package com.lti.service;

import java.util.List;

import com.lti.entity.Claim;

public interface DashboardService {

	List<Claim> fetchByProcessingStatusService(String processingStatus);

	long getCountCustomerService();

	long getCountClaimService();

	long getCountPolicyService();
	
	long getCountRenewService();

}