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
import com.lti.entity.Vehicle;
import com.lti.repository.BuyInsuranceRepository;
import com.lti.repository.ClaimInsuranceRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class BuyInsuranceTestCases {

	@Autowired
	private BuyInsuranceRepository buyInsuranceRepo;
	
	
	@Test
	void submitDetailsForBuyingInsurance() {
		Vehicle v = new Vehicle();
		v.setType("Four Wheeler");
		v.setModel("S-Cross");
		v.setManufacture("Maruti");
		v.setDrivingLicense("QWERTY12");
		v.setPurchaseDate(LocalDate.of(2019, 11, 19));
		v.setRegistrationNo("ASDFG123");
		v.setEngineNo("mnbvcxz");
		v.setChassisNo("zxcvbnm");
		v.setEngineType("200 HP");
		
		Customer cust = new Customer();
		cust.setName("Archit");
		cust.setEmailId("aryany996699@gmail.com");
		cust.setDateOfBirth(LocalDate.of(1997, 11, 19));
		cust.setContactNo(975883345);
		
		Address addr = new Address();
		addr.setCity("Mainpuri");
		addr.setPincode(282008);
		addr.setLandMark("Christian Field");
		
		cust.setAddress(addr);
		
		v.setCustomer(cust);
		
		buyInsuranceRepo.submissionOfBuyInsuranceData(v);
		
	}
	
	@Test
	public void isCheckInsuranceWorking() {
		boolean answer = buyInsuranceRepo.isSameVehicleIsInsuredAlready("ASDFG123");
		System.out.println(answer);
	}
}
