package com.lti.repository;

import java.util.List;

import com.lti.entity.Claim;

public interface DashboardRepository {

	List<Claim> fetchByProcessingStatus(String processingStatus);

	long getCountCustomer();

	long getCountClaim();

	long getCountPolicy();
	
	long getCountRenew();
	
	long getUserInsuranceCount(int id);
	
	long getUserClaimCount(int id);
	
	long getUserRenewCount(int id);
	
	long getUserDashboardData(int id);

}