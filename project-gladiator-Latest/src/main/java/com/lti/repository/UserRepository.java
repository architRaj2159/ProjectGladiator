package com.lti.repository;

import java.util.List;

import com.lti.entity.Customer;
import com.lti.entity.User;

public interface UserRepository {

    void save(Customer customer);
    
    void save(User user);

	Customer findCustomerById(int id);

	List<Customer> findAll();

	User findByEmailPassword(String email, String password);

	boolean isCustomerPresent(String email);

	boolean isUserPresent(String email);
	
	User findUserByOtp(String otp);
	 
	User findUserByEmail(String email);

}