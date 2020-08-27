package com.lti.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.EstimateStatusDto;
import com.lti.dto.EstimateStatusDto.StatusType;
import com.lti.dto.PremiumDto;
import com.lti.entity.Premium;
import com.lti.exception.EstimateServiceException;
import com.lti.service.EstimateService;

@RestController
@CrossOrigin
public class EstimateController {

	@Autowired
	private EstimateService estimateService;

	@PostMapping(path = "/estimate", consumes = "application/json", produces = "application/json")
	public EstimateStatusDto estimate(@RequestBody PremiumDto premiumDto) {
		try {
			Premium premium = new Premium();
			BeanUtils.copyProperties(premiumDto, premium);
			premium.setDepreciationRate(2*premium.getAge());
					
			System.out.println(premium.toString());
			
			double estimatedAmt = estimateService.estimateClaim(premium);
			
			estimateService.saveData(premium);
			
			EstimateStatusDto status = new EstimateStatusDto();
			status.setMessage("SUCCESS");
			status.setEstimateAmount(estimatedAmt);
			status.setStatus(StatusType.SUCCESS);
			
			
			
			return status;
			
		} catch (EstimateServiceException e) {
			System.out.println(e.getMessage());
		}
		
		
		EstimateStatusDto status = new EstimateStatusDto();
		status.setMessage("FAILURE");
		status.setEstimateAmount(-1);
		status.setStatus(StatusType.FAILURE);
		return status;
	}
}
