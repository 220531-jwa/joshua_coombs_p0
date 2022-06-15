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
	//private static ClientDAO clientDao = new ClientDAO();
	
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
	
	public boolean updateAccount(Account changeAccount) {
		return accountDao.updateAccount(changeAccount);
	}
	
	public boolean deleteAccount(int clientId, int accountNumber) {
		return accountDao.deleteAccount(clientId, accountNumber);
	}
}
