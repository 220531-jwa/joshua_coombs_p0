/**
 * This ClientAccountLeftJoin class is a model for left joins of the clients
 * and accounts tables within the database which is used in AccountDAO,
 * AccountService, and AccountController. This class has both the checking account
 * amount and the savings account amount
 * 
 * @author joshua_coombs
 * @version 1.0
 */

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
	
	/**
	 * This is a getter method for the id
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * This is a setter method for the id
	 * 
	 * @return
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * This is a getter method for the firstName
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * This is a setter method for the firstName
	 * 
	 * @return
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * This is a getter method for the lastName
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * This is a setter method for the lastName
	 * 
	 * @return
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * This is a getter method for the accountNumber
	 * 
	 * @return
	 */
	public int getAccountNumber() {
		return accountNumber;
	}

	/**
	 * This is a setter method for the accountNumber
	 * 
	 * @return
	 */
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * This is a getter method for the checkingAmount
	 * 
	 * @return
	 */
	public int getCheckingAmount() {
		return checkingAmount;
	}

	/**
	 * This is a setter method for the checkingAmount
	 * 
	 * @return
	 */
	public void setCheckingAmount(int checkingAmount) {
		this.checkingAmount = checkingAmount;
	}

	/**
	 * This is a getter method for the savingsAmount
	 * 
	 * @return
	 */
	public int getSavingsAmount() {
		return savingsAmount;
	}

	/**
	 * This is a setter method for the savingsAmount
	 * 
	 * @return
	 */
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
