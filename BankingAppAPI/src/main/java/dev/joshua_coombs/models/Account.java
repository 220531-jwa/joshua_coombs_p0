package dev.joshua_coombs.models;

public class Account {
	private int clientId;
	private int accountNumber;
	private int checkingAmount;
	private int savingsAmount;
	
	public Account() {
		super();
	}
	
	public Account(int accountNumber, int clientId, int checkingAmount, int savingsAmount) {
		super();
		this.accountNumber = accountNumber;
		this.clientId = clientId;
		this.checkingAmount = checkingAmount;
		this.savingsAmount = savingsAmount;
	}
	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCheckingAmount() {
		return checkingAmount;
	}

	public void setCheckingAmount(int checkingAmount) {
		this.checkingAmount = checkingAmount;
	}

	public int getSavingsAmount() {
		return savingsAmount;
	}

	public void setSavingsAmount(int savingsAmount) {
		this.savingsAmount = savingsAmount;
	}
	
	@Override
	public String toString() {
		return "[account_number=" + accountNumber  + ", client_id=" + clientId + 
				", checking=" + checkingAmount + ", savings=" + savingsAmount + "]";
	}
}
