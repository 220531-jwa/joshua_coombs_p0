/**
 * This AccountService class handles methods defined within the AccountDAO
 * class. The methods of this class are accessed by the AccountController
 * class.
 * 
 * @author joshua_coombs
 * @version 1.0
 */

package dev.joshua_coombs.services;

import java.util.List;

import dev.joshua_coombs.models.Account;
import dev.joshua_coombs.models.AlternateCALeftJoin;
import dev.joshua_coombs.models.ClientAccountLeftJoin;
import dev.joshua_coombs.repositories.AccountDAO;

public class AccountService {
	private static AccountDAO accountDao = new AccountDAO();
	
	/*
	public AccountService(AccountDAO accountDao) {
		this.accountDao = accountDao;
	}
	*/
	
	/**
	 * This method creates an account within the accounts table of
	 * the database which is associated with a specific client id
	 * 
	 * @param a
	 * @return
	 */
	public Account createAccount(Account a) {
		Account newAccount = accountDao.createAccount(a);
		return newAccount;
	}
	
	/**
	 * This method gets all the accounts associated with a specific client id
	 * within the database
	 * 
	 * @param clientId
	 * @return
	 */
	public List<ClientAccountLeftJoin> getAllAccountsByClientId(int clientId) {
		return accountDao.getAllAccountsByClientId(clientId);
	}
	
	/**
	 * This method gets a specific account associated with a specific client id
	 * within the database
	 * 
	 * @param clientId
	 * @param accountNumber
	 * @return
	 * @throws Exception
	 */
	public ClientAccountLeftJoin getSpecificAccountByClientId(int clientId, int accountNumber) throws Exception {
		return accountDao.getSpecificAccountByClientId(clientId, accountNumber);
	}
	
	/**
	 * This method gets all the accounts associated with a specific client id
	 * which abide by a certain value range
	 * 
	 * @param clientId
	 * @param whichType
	 * @param low
	 * @param high
	 * @return
	 */
	public AlternateCALeftJoin getAccountsInValueRange(int clientId, String whichType, int low, int high) {
		return accountDao.getAccountsInValueRange(clientId, whichType, low, high);
	}
	
	/**
	 * This method updates a specific account associated with a specific client id
	 * 
	 * @param clientId
	 * @param accountNumber
	 * @param checkingAmount
	 * @param savingsAmount
	 * @return
	 */
	public boolean updateAccount(int clientId, int accountNumber, int checkingAmount, int savingsAmount) {
		return accountDao.updateAccount(clientId, accountNumber, checkingAmount, savingsAmount);
	}
	
	/**
	 * This method withdraws a certain amount from either a checking or savings
	 * sub-account, within a specific account associated with a client id
	 * 
	 * @param clientId
	 * @param accountNumber
	 * @param whichType
	 * @param amountToWithdraw
	 * @return
	 */
	public boolean withdraw(int clientId, int accountNumber, String whichType, int amountToWithdraw) {
		return accountDao.withdraw(clientId, accountNumber, whichType, amountToWithdraw);
	}
	
	/**
	 * This method deposits a certain amount into either a checking or savings
	 * sub-account, within a specific account associated with a client id
	 * 
	 * @param clientId
	 * @param accountNumber
	 * @param whichType
	 * @param amountToDeposit
	 * @return
	 */
	public boolean deposit(int clientId, int accountNumber, String whichType, int amountToDeposit) {
		return accountDao.deposit(clientId, accountNumber, whichType, amountToDeposit);
	}
	
	/**
	 * This method transfers a certain amount from one account (either 
	 * checking or savings) and to another account (either checking or 
	 * savings), both of which are associated with a specific client id
	 * 
	 * @param clientId
	 * @param fromAccount
	 * @param toAccount
	 * @param fromWhichType
	 * @param toWhichType
	 * @param amount
	 * @return
	 */
	public boolean transfer(int clientId, int fromAccount, int toAccount, 
			String fromWhichType, String toWhichType, int amount) {
		return accountDao.transfer(clientId, fromAccount, toAccount, 
			fromWhichType, toWhichType, amount);
	}
	
	/**
	 * This methods deletes a specific account associated with a specific client id
	 * 
	 * @param clientId
	 * @param accountNumber
	 * @return
	 */
	public boolean deleteAccount(int clientId, int accountNumber) {
		return accountDao.deleteAccount(clientId, accountNumber);
	}
}
