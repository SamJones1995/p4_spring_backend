package com.revature.dtos;

public class TransferRequest {
	
	private String accountId1;
	
	private String accountId2;
	
	private String amount;

	public String getAccountId1() {
		return accountId1;
	}

	public void setAccountId1(String accountId1) {
		this.accountId1 = accountId1;
	}

	public String getAccountId2() {
		return accountId2;
	}

	public void setAccountId2(String accountId2) {
		this.accountId2 = accountId2;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	

}
