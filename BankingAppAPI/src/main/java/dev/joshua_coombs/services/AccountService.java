package dev.joshua_coombs.services;

import java.util.List;
import dev.joshua_coombs.models.Account;
import dev.joshua_coombs.repositories.AccountDAO;

public class AccountService {
	private static AccountDAO accountDao = new AccountDAO();
	
	public Account createAccount(Account a) {
		Account newAccount = accountDao.createAccount(a);
		return newAccount;
	}
	
	public List<Account> getAllAccountsByClientId(int clientId) {
		return accountDao.getAllAccountsByClientId(clientId);
	}
	
	public Account getSpecificAccountByClientId(int accountNumber, int clientId) throws Exception {
		Account a = accountDao.getSpecificAccountByClientId(accountNumber, clientId);
		if (a == null) {
			throw new Exception("Account not found");
		}
		return a;
	}
	
	public void updateAccount(Account changeAccount) {
		accountDao.updateAccount(changeAccount);
	}
	
	public void deleteAccount(int accountNumber, int clientId) {
		accountDao.deleteAccount(accountNumber, clientId);
	}
}
