package dev.joshua_coombs.models;

import java.util.HashMap;
import java.util.Map;

public class Account {
	private int accountNumber;
	private Map<Integer, Integer> accounts = new HashMap<>();
	
	public Account() {
		super();
	}
	
	public Account(Map<Integer, Integer> accounts) {
		this.accounts = accounts;
	}
	
	public Map<Integer, Integer> getAllAccounts() {
		return accounts;
	}
	
	public int getSpecificAccount(int n) {
		for (int key : accounts.keySet()) {
			if (key == n) {
				return key;
			}
		}
		return 0;
	}
	
	public int getSpecificAccountBalance(int n) {
		return accounts.get(n);
	}
	
	public void setAccounts(Map<Integer, Integer> accounts) {
		this.accounts = accounts;
	}
	
	public void setSpecificAccountBalance(int n, int a) {
		int newAmount = accounts.get(n);
		newAmount = a;
	}
	
	@Override
	public String toString() {
		String result = "Accounts ";
		for (Integer n : accounts.keySet()) {
			result += "[accountNumber=" + n + "balance=" + getSpecificAccountBalance(n) + "]";
		}
		return result;
	}
}
