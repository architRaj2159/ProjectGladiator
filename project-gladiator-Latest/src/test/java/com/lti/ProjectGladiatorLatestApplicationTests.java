package com.lti;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lti.entity.Address;
import com.lti.entity.Customer;
import com.lti.entity.Vehicle;
import com.lti.repository.BuyInsuranceRepositoryImpl;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class ProjectGladiatorLatestApplicationTests {

	@Autowired
	private BuyInsuranceRepositoryImpl buyInsuranceRepo;
	
	
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
		cust.setPassword("123456");
		
		Address addr = new Address();
		addr.setCity("Agra");
		addr.setPincode(282007);
		addr.setLandMark("Sikandra");
		
		cust.setAddress(addr);
		
		v.setCustomer(cust);
		
		buyInsuranceRepo.submitData(v);
		
	}

}
