package com.synechron.enbdaccountcrud.model;

import java.util.List;

public class AccountsList {
	
	private List<Account> accountsList;

	public AccountsList() {
	}

	public AccountsList(List<Account> accountsList) {
		super();
		this.accountsList = accountsList;
	}

	public List<Account> getAccountsList() {
		return accountsList;
	}

	public void setAccountsList(List<Account> accountsList) {
		this.accountsList = accountsList;
	}
}
