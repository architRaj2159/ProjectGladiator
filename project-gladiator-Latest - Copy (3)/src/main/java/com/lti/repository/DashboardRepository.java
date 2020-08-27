package com.lti.repository;

import java.util.List;

import com.lti.entity.Claim;

public interface DashboardRepository {

	List<Claim> fetchByProcessingStatus(String processingStatus);

	long getCountCustomer();

	long getCountClaim();

	long getCountPolicy();
	
	long getCountRenew();

}