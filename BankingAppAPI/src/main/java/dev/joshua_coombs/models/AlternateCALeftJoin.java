package dev.joshua_coombs.models;

public class AlternateCALeftJoin {
	private int id;
	private String firstName;
	private String lastName;
	private int accountNumber;
	private int whichAmount;
	
	public AlternateCALeftJoin() {
		super();
	}
	
	public AlternateCALeftJoin(int id, String firstName, String lastName, int accountNumber,
			int whichAmount) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		this.whichAmount = whichAmount;
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

	public int getWhichAmount() {
		return whichAmount;
	}

	public void setWhichAmount(int whichAmount) {
		this.whichAmount = whichAmount;
	}

	@Override
	public String toString() {
		return "AlternateCALeftJoin [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", accountNumber=" + accountNumber + ", whichAmount=" + whichAmount + "]";
	}
}
