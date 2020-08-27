package com.lti;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lti.entity.Address;
import com.lti.entity.Claim;
import com.lti.entity.Customer;
import com.lti.entity.InsurancePlan;
import com.lti.entity.Policy;
import com.lti.repository.ClaimInsuranceRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class ClaimInsuranceTestCases {
	
	@Autowired
	private ClaimInsuranceRepository claimInsuranceRepo;
	
	@Test
	public void testCaseForSubmitClaimDetails(){
		Claim claim = new Claim();
		claim.setDate(LocalDate.of(2014, 11, 14));
		claim.setStatus("Not Approved.");
		claim.setAmount(95748);
		claim.setReason("Drought");
		claim.setContactNo(876543219);
		
		Customer cust = new Customer();
		cust.setName("Ankur Singh");
		cust.setEmailId("ankur.singh@gmail.com");
		cust.setDateOfBirth(LocalDate.of(1996, 9, 16));
		cust.setContactNo(876543219);
		
		Address addr = new Address();
		addr.setCity("Azamgarh");
		addr.setPincode(321456);
		addr.setLandMark("Mirzapur");
		
		cust.setAddress(addr);
		claim.setCustomer(cust);
		
		Policy policy = new Policy();
		policy.setStatus("Not Approved");
		policy.setIssueDate(LocalDate.of(2018, 12, 10));
		policy.setExpiryDate(LocalDate.of(2019, 12, 25));
		policy.setDuration(12);
		policy.setPolicyAmount(40000);
		policy.setPlan_amount(31000);
		policy.setCustomer(cust);
		
		InsurancePlan ip = new InsurancePlan();
		ip.setName("madhuban");
		ip.setType("Motor Vehicle Plan");
		ip.setDuration(12);
		ip.setAmount(35000);
		ip.setCommission(24000);
		
		policy.setInsurancePlan(ip);
		
		claim.setPolicy(policy);
		
		claimInsuranceRepo.submitAndUpdatingOfClaimInsurance(claim);	
	}
	
	@Test
	public void TestCaseForUpdateClaimStatus() {
		Claim claim = claimInsuranceRepo.fetchDataByIdForClaimInsurance(31);
		
		claim.setStatus("Not Approved");
		claimInsuranceRepo.submitAndUpdatingOfClaimInsurance(claim);
	}
	
	@Test
	public void TestCaseForFetchDataById() {
		Claim claim = claimInsuranceRepo.fetchDataByIdForClaimInsurance(16);
		
		System.out.println(claim.getId()+ " "+claim.getReason()+ " " +claim.getAmount()+ " " +claim.getStatus()+ " "+claim.getDate());
	}
	
	@Test
	public void TestCaseForFetchAllData() {
		List<Claim> list = claimInsuranceRepo.fetchAllClaimData();
		for(Claim c: list) {
			System.out.println(c.getId()+ " "+c.getReason()+ " " +c.getAmount()+ " " +c.getStatus()+ " "+c.getDate());
		}
	}

}