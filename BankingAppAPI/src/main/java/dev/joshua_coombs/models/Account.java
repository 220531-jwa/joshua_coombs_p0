/**
 * This Account class is a model for the accounts table within the database 
 * which is used in AccountDAO, AccountService, and AccountController.
 * 
 * @author joshua_coombs
 * @version 1.0
 */

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
	
	/**
	 * This is a getter method for the clientId
	 * 
	 * @return
	 */
	public int getClientId() {
		return clientId;
	}

	/**
	 * This is a setter method for the clientId
	 * 
	 * @return
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
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
		return "[account_number=" + accountNumber  + ", client_id=" + clientId + 
				", checking=" + checkingAmount + ", savings=" + savingsAmount + "]";
	}
}
