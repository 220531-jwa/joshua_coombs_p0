package dev.joshua_coombs.controllers;

import java.util.List;

import dev.joshua_coombs.models.Account;
import dev.joshua_coombs.models.AlternateCALeftJoin;
import dev.joshua_coombs.models.Client;
import dev.joshua_coombs.models.ClientAccountLeftJoin;
import dev.joshua_coombs.repositories.AccountDAO;
import dev.joshua_coombs.repositories.ClientDAO;
import dev.joshua_coombs.services.AccountService;
import dev.joshua_coombs.services.ClientService;
import io.javalin.http.Context;

public class AccountController {
	private static AccountService accountService = new AccountService(new AccountDAO());
	private static ClientService clientService = new ClientService(new ClientDAO());
	
	public static void createAccount(Context ctx) {
		
		Account accountFromRequestBody = ctx.bodyAsClass(Account.class);
		Account a = accountService.createAccount(accountFromRequestBody);
		if (a != null) {
			ctx.status(201);
			ctx.json(a);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getAllAccountsByClientId(Context ctx) {
		int clientId = Integer.parseInt(ctx.pathParam("id"));
		Client verifyId = null;
		try {
			verifyId = clientService.getClientById(clientId);
			if (verifyId != null) {
				List<ClientAccountLeftJoin> accounts = accountService.getAllAccountsByClientId(clientId);
				ctx.status(200);
				ctx.json(accounts);
			} else {
				ctx.status(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getSpecificAccountByClientId(Context ctx) {
		int clientId = Integer.parseInt(ctx.pathParam("id"));
		int accountNumber = Integer.parseInt(ctx.pathParam("account_number"));
		ClientAccountLeftJoin verifyAccount = null;
		try {
			verifyAccount = accountService.getSpecificAccountByClientId(clientId, accountNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ctx.status(200);
		ctx.json(verifyAccount);
	}
	
	public static void getAccountsInValueRange(Context ctx) {
		int clientId = Integer.parseInt(ctx.pathParam("id"));
		String whichType = ctx.pathParam("which_type"); //finish
		String amountLessThan = ctx.queryParam("amountLessThan");
		String amountGreaterThan = ctx.queryParam("amountGreaterThan");
		int low = Integer.parseInt(amountGreaterThan);
		int high = Integer.parseInt(amountLessThan);
		AlternateCALeftJoin verifyAccount = null;
		try {
			verifyAccount = accountService.getAccountsInValueRange(clientId, whichType, low, high);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ctx.status(200);
		ctx.json(verifyAccount);
	}
	
	public static void updateAccount(Context ctx) {
		int accountNumber = Integer.parseInt(ctx.pathParam("account_number"));
		int clientId = Integer.parseInt(ctx.pathParam("client_id")); //maybe change to id
		Account changeAccount = ctx.bodyAsClass(Account.class);
		changeAccount.setClientId(clientId);
		changeAccount.setAccountNumber(accountNumber);
		boolean updated = accountService.updateAccount(changeAccount);
		if (!updated) {
			ctx.status(404);
		} else {
			ctx.status(200);
		}
	}
	public static void deleteAccount(Context ctx) {
		int accountNumber = Integer.parseInt(ctx.pathParam("account_number"));
		int clientId = Integer.parseInt(ctx.pathParam("client_id"));
		boolean deletedAccount = accountService.deleteAccount(clientId, accountNumber);
		if (!deletedAccount) {
			ctx.status(404);
		} else {
			ctx.status(205);
		}
	}
	
	public static void withdrawFromAccount(Context ctx) {
		
	}
	
	public static void depositIntoAccount(Context ctx) {
		
	}
	
	public static void transferFunds(Context ctx) {
		
	}
}
