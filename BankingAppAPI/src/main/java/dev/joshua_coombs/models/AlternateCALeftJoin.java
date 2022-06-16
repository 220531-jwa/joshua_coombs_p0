/**
 * This AlternateCALeftJoin class is a model for left joins of the clients
 * and accounts tables within the database which is used in AccountDAO,
 * AccountService, and AccountController. This account has either the checking account
 * amount, or the savings account amount, never both like ClientAccountLeftJoin
 * 
 * @author joshua_coombs
 * @version 1.0
 */

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
	 * This is a getter method for whichAmount (which will either be
	 * a checkingAmount or a savingsAmount)
	 * 
	 * @return
	 */
	public int getWhichAmount() {
		return whichAmount;
	}

	/**
	 * This is a setter method for the whichAmount (which will either be
	 * a checkingAmount or a savingsAmount)
	 * 
	 * @return
	 */
	public void setWhichAmount(int whichAmount) {
		this.whichAmount = whichAmount;
	}

	@Override
	public String toString() {
		return "AlternateCALeftJoin [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", accountNumber=" + accountNumber + ", whichAmount=" + whichAmount + "]";
	}
}
