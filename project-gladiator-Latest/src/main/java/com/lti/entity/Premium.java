
package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PREMIUM_ESTIMATE_MOTOR")
public class Premium {

	@Id
	@SequenceGenerator(name = "id", initialValue = 1, allocationSize = 1)
	@GeneratedValue
	private int id;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "model", nullable = false)
	private String model;

	@Column(name = "amount", nullable = false)
	private double amount;

	@Column(name = "depreciationRate", nullable = false)
	private int depreciationRate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getDepreciationRate() {
		return depreciationRate;
	}

	public void setDepreciationRate(int depreciationRate) {
		this.depreciationRate = depreciationRate;
	}

}
