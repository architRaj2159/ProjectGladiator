package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Claim;
import com.lti.entity.Customer;
import com.lti.entity.Policy;
import com.lti.entity.Vehicle;

@Repository
public class ClaimInsuranceRepositoryImpl implements ClaimInsuranceRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void submitAndUpdatingOfClaimInsurance(Claim claim) {
		entityManager.merge(claim);
	}

	@Override
	public Claim fetchDataByIdForClaimInsurance(int id) {
		Claim claim = entityManager.find(Claim.class, id);
		return claim;
	}

	@Override
	public List<Claim> fetchAllClaimData() {
		Query q = entityManager.createQuery("select claim from Claim as claim");
		List<Claim> list = q.getResultList();
		return list;
	}

	@Override
	public Customer findByCustomerId(int id) {
		return entityManager.find(Customer.class, id);
	}

	@Override
	public Policy findByPolicyId(int id) {
		return entityManager.find(Policy.class, id);
	}
}
