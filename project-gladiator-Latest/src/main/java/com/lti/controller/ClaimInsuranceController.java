package com.lti.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ApproveClaimDto;
import com.lti.dto.ClaimDto;
import com.lti.dto.GetAllClaims;
import com.lti.entity.Claim;
import com.lti.entity.Customer;
import com.lti.entity.Policy;
import com.lti.exception.InsuranceServiceException;
import com.lti.exception.UserServiceException;
import com.lti.service.ClaimInsuranceService;
import com.lti.service.EmailService;
import com.lti.status.Status;
import com.lti.status.Status.StatusType;

@RestController
@CrossOrigin
public class ClaimInsuranceController {
	@Autowired
	private ClaimInsuranceService service;

	@Autowired
	private EmailService emailService;
	
	
	@PostMapping(path = "/addClaimInsurance", consumes = "application/json", produces = "application/json")
	public Status submitClaimDetails(@RequestBody ClaimDto claimDto) {
		try {

			Claim claim = new Claim();
			claim.setDate(claimDto.getDate());
			claim.setStatus("PENDING");
			claim.setAmount(claimDto.getAmount());
			claim.setReason(claimDto.getReason());
			claim.setContactNo(claimDto.getContactNo());

			Customer customer = service.findByCustomerId(claimDto.getCustomerId());
			claim.setCustomer(customer);

			Policy policy = service.findByPolicyId(claimDto.getPolicyId());
			claim.setPolicy(policy);

			service.saveAndUpdateOfClaimInsuranceDetails(claim);

			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Claim Details Are Submitted Successfully.");
			return status;
		} catch (InsuranceServiceException e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping(path = "/updateClaimStatus", consumes = "application/json", produces = "application/json")
	public Status updateClaimStatus(@RequestBody ApproveClaimDto approveClaimDto) {
		try {

			// Check if session id is of ADMIN role from User table -not added
			// is role is not admin then throw InsuranceServiceException
			
			Claim claim = service.getClaimDataById(approveClaimDto.getClaimId());
			
			if(claim == null) {
				throw new UserServiceException("Invalid claim id");
			}
			
			claim.setStatus(approveClaimDto.getStatus());

			service.saveAndUpdateOfClaimInsuranceDetails(claim);
			
			try {
	            SimpleMailMessage registeredMail = new SimpleMailMessage();
				registeredMail.setFrom("nk.theraja@gmail.com"); // email of sender
				registeredMail.setTo(claim.getCustomer().getEmailId());
				registeredMail.setSubject("Claim Status Change");
				registeredMail.setText("Your claim " + claim.getId() + " is " + claim.getStatus());

				//emailService.sendEmail(registeredMail);

			} catch (MailException e) {
				throw new UserServiceException("Error occured during sending email!");
			}
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Claim status is updated Successfully.");
			return status;

		} catch (Exception e) {
			e.printStackTrace();
			Status status = new Status();
			status.setMessage(e.getMessage());
			status.setStatus(StatusType.FAILURE);

			return status;
		}
	}
}
