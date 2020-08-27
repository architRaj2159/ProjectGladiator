package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.lti.controller.RenewController.Status.StatusType;
//import com.lti.controller.RenewController.Status;
//import com.lti.controller.RenewController.Status.StatusType;
import com.lti.entity.Policy;
import com.lti.service.RenewService;
import com.lti.exception.RenewServiceException;
import com.lti.repository.RenewRepository;

@RestController
@CrossOrigin
public class RenewController {
	
	@Autowired
	private RenewService renewService;
	
	@Autowired
	private RenewRepository renewRepo;
	
	@PostMapping(path = "/renew", consumes = "application/json", produces = "application/json")
	public Policy renew(@RequestBody Policy policy) {
		Policy newPolicy = new Policy();
	  try {
		  //System.out.println(policy.getId()+" "+policy.getDuration()+" "+policy.getExpiryDate()+" "+policy.getIssueDate()+" "+policy.getPlanAmount()+" "+policy.getPolicyAmount()+" "+policy.getStatus());
		  renewService.renewIns(policy);
		  //System.out.println("Renewed");
		  newPolicy =  renewRepo.findByPolicyId(policy.getId());
		  
		
		//System.out.println("renewed");
		//return "success";
		
		//Status status = new Status();
		//status.setStatus(StatusType.SUCCESS);
		//status.setMessage("Policy renewed!");
		//return status;
	   }
		catch(RenewServiceException e) {
			//Status status = new Status();
			//status.setStatus(StatusType.FAILURE);
			//status.setMessage(e.getMessage());
			//return status;
			System.out.println(e.getMessage());
		//}
			//return newPolicy;
	}
	return newPolicy;
	
	/*public static class Status{
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
		
	}*/
}
}
