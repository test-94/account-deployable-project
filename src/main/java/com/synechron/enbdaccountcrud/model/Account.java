package com.synechron.enbdaccountcrud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
public class Account {

	@Transient
	public final static String SEQUENCE_NAME = "account_sequence";
	
	@Id
	private int id;
	private String name;
	private double totalBalance;
	
	public Account() {
		super();
	}

	public Account(int id, String name, double totalBalance) {
		super();
		this.id = id;
		this.name = name;
		this.totalBalance = totalBalance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", name='" + name + '\'' +
				", totalBalance=" + totalBalance +
				'}';
	}
}
