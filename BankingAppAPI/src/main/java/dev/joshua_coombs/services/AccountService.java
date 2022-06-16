package dev.joshua_coombs.services;

import java.util.List;

import dev.joshua_coombs.models.Account;
import dev.joshua_coombs.models.AlternateCALeftJoin;
import dev.joshua_coombs.models.ClientAccountLeftJoin;
import dev.joshua_coombs.repositories.AccountDAO;

public class AccountService {
	private static AccountDAO accountDao;
	
	public AccountService(AccountDAO accountDao) {
		this.accountDao = accountDao;
	}
	
	public Account createAccount(Account a) {
		Account newAccount = accountDao.createAccount(a);
		return newAccount;
	}
	
	public List<ClientAccountLeftJoin> getAllAccountsByClientId(int clientId) {
		return accountDao.getAllAccountsByClientId(clientId);
	}
	
	public ClientAccountLeftJoin getSpecificAccountByClientId(int clientId, int accountNumber) throws Exception {
		return accountDao.getSpecificAccountByClientId(clientId, accountNumber);
	}
	
	public AlternateCALeftJoin getAccountsInValueRange(int clientId, String whichType, int low, int high) {
		return accountDao.getAccountsInValueRange(clientId, whichType, low, high);
	}
	
	public boolean updateAccount(int clientId, int accountNumber, int checkingAmount, int savingsAmount) {
		return accountDao.updateAccount(clientId, accountNumber, checkingAmount, savingsAmount);
	}
	
	public boolean withdraw(int clientId, int accountNumber, String whichType, int amountToWithdraw) {
		return accountDao.withdraw(clientId, accountNumber, whichType, amountToWithdraw);
	}
	
	public boolean deposit(int clientId, int accountNumber, String whichType, int amountToDeposit) {
		return accountDao.deposit(clientId, accountNumber, whichType, amountToDeposit);
	}
	
	public boolean transfer(int clientId, int fromAccount, int toAccount, 
			String fromWhichType, String toWhichType, int amount) {
		return accountDao.transfer(clientId, fromAccount, toAccount, 
			fromWhichType, toWhichType, amount);
	}
	
	public boolean deleteAccount(int clientId, int accountNumber) {
		return accountDao.deleteAccount(clientId, accountNumber);
	}
}
