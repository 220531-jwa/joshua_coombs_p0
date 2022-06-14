package dev.joshua_coombs.services;

import dev.joshua_coombs.models.Account;
import dev.joshua_coombs.models.ClientAccountLeftJoin;
import dev.joshua_coombs.repositories.AccountDAO;

public class AccountService {
	private static AccountDAO accountDao = new AccountDAO();
	//private static ClientDAO clientDao = new ClientDAO();
	
	public Account createAccount(Account a) {
		Account newAccount = accountDao.createAccount(a);
		return newAccount;
	}
	
	public ClientAccountLeftJoin getAllAccountsByClientId(int clientId) {
		return accountDao.getAllAccountsByClientId(clientId);
	}
	
	public ClientAccountLeftJoin getSpecificAccountByClientId(int clientId, int accountNumber) throws Exception {
		ClientAccountLeftJoin a = accountDao.getSpecificAccountByClientId(clientId, accountNumber);
		if (a == null) {
			throw new Exception("Account not found");
		}
		return a;
	}
	
	public boolean updateAccount(Account changeAccount) {
		return accountDao.updateAccount(changeAccount);
	}
	
	public boolean deleteAccount(int clientId, int accountNumber) {
		return accountDao.deleteAccount(clientId, accountNumber);
	}
}
