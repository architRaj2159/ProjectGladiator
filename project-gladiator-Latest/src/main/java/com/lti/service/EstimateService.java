package com.lti.service;

import com.lti.entity.Premium;

public interface EstimateService {

	double estimateClaim(Premium premium);
	
	void saveData(Premium premium);

}