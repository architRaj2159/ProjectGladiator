package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lti.controller.BuyInsuranceController.Status.StatusType;
import com.lti.entity.Vehicle;
import com.lti.exception.BuyInsuranceServiceException;
import com.lti.service.BuyInsuranceService;

@Controller
public class BuyInsuranceController {

	@Autowired
	private BuyInsuranceService service;
	
	@PostMapping("/buyInsurance")
	public Status submissionDetail(@RequestBody Vehicle vehicle) {
		try {
			service.submissionOfInsuranceDetails(vehicle);
			
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Details Are Submitted for Verification.");
			return status;
		}
		catch(BuyInsuranceServiceException e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	public static class Status{
		private StatusType status;
		private String message;
		
		public static enum StatusType{
			SUCCESS, FAILURE;
		}

		public StatusType getStatus() {
			return status;
		}

		public void setStatus(StatusType status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		
	}
}
