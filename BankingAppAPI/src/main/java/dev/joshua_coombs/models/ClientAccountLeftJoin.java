package dev.joshua_coombs.models;

public class ClientAccountLeftJoin {
	private int id;
	private String firstName;
	private String lastName;
	private int accountNumber;
	private int checkingAmount;
	private int savingsAmount;
	
	public ClientAccountLeftJoin() {
		super();
	}
	
	public ClientAccountLeftJoin(int id, String firstName, String lastName, int accountNumber,
			int checkingAmount, int savingsAmount) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		this.checkingAmount = checkingAmount;
		this.savingsAmount = savingsAmount;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return "ClientAccountLeftJoin [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", accountNumber=" + accountNumber + ", checkingAmount=" + checkingAmount
				+ ", savingsAmount=" + savingsAmount + "]";
	}
	
	
}
