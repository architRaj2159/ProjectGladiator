package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Customer;
import com.lti.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Customer customer) {
		entityManager.merge(customer);
    }
    
    @Override
	@Transactional
	public void save(User user) {
		entityManager.merge(user);
	}

	@Override
	public Customer findCustomerById(int id) {
		return entityManager.find(Customer.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAll() {
		return entityManager.createNamedQuery("fetch-all").getResultList();
	}

	@Override
	public boolean isCustomerPresent(String email) {
		return ((Number)entityManager.createNamedQuery("is-customer-present")
				.setParameter("em", email)
				.getSingleResult()).intValue()== 1? true:false;
	}
	
	@Override
	public boolean isUserPresent(String email) {
		return ((Number)entityManager.createNamedQuery("is-user-present")
				.setParameter("em", email)
				.getSingleResult()).intValue()== 1? true:false;
	}

	@Override
	public User findByEmailPassword(String email, String password) {
		return (User) entityManager.createNamedQuery("fetch-login")
				.setParameter("email", email)
				.setParameter("password", password)
				.getSingleResult();
	}

	@Override
	public User findUserByOtp(String otp) {
		return (User) entityManager.createNamedQuery("fetch-user-using-otp")
				.setParameter("otp", otp)
				.getSingleResult();
	}

	@Override
	public User findUserByEmail(String email) {
		return (User) entityManager.createNamedQuery("find-user-by-email")
				.setParameter("email", email)
				.getSingleResult();
	}
}