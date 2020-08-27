package com.lti.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.VehicleDto;
import com.lti.entity.Customer;
import com.lti.entity.Vehicle;
import com.lti.exception.InsuranceServiceException;
import com.lti.service.BuyInsuranceService;
import com.lti.status.Status;
import com.lti.status.Status.StatusType;

@RestController
@CrossOrigin
public class BuyInsuranceController {

	@Autowired
	private BuyInsuranceService service;
	
	@PostMapping(path="/buyInsurance", consumes = "application/json", produces = "application/json")
	public Status submissionDetail(@RequestBody VehicleDto vehicleDto) {
		try {
			
			Vehicle vehicle = new Vehicle();
			//BeanUtils.copyProperties(vehicleDto, vehicle);
			
			vehicle.setChassisNo(vehicleDto.getChassisNo());
			vehicle.setDrivingLicense(vehicleDto.getDrivingLicense());
			vehicle.setEngineType(vehicleDto.getEngineType());
			vehicle.setEngineNo(vehicleDto.getEngineNo());
			vehicle.setManufacturer(vehicleDto.getManufacture());
			vehicle.setModel(vehicleDto.getModel());
			vehicle.setType(vehicleDto.getType());
			vehicle.setPurchaseDate(vehicleDto.getPurchaseDate());
			vehicle.setLastRenewDate(vehicleDto.getLastRenewDate());
			vehicle.setRegistrationNo(vehicleDto.getRegistrationNo());
			
			
			Customer customer = service.findById(vehicleDto.getCustomerId());
			
			vehicle.setCustomer(customer);
			
			service.submissionOfBuyInsuranceDetails(vehicle);
			
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Details Are Submitted for Verification.");
			return status;
		}
		catch(InsuranceServiceException e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}
}
