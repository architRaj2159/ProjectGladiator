package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Customer;
import com.lti.entity.Vehicle;

@Repository
public class BuyInsuranceRepositoryImpl implements BuyInsuranceRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void submitData(Vehicle vehicle) {
		entityManager.merge(vehicle);
	}
	
	
	public boolean isSameVehicleIsInsuredAlready(String registrationNo) {
		return (Long) entityManager.createQuery("select count(v.id) from Vehicle as v where v.registration= :reNo ")
				.setParameter("reNo", registrationNo)
				.getSingleResult() == 1 ? true : false;
	}
}
