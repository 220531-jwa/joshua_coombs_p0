package dev.joshua_coombs.controllers;

import java.util.List;

import dev.joshua_coombs.models.Account;
import dev.joshua_coombs.models.Client;
import dev.joshua_coombs.services.AccountService;
import dev.joshua_coombs.services.ClientService;
import io.javalin.http.Context;

public class AccountController {
	private static AccountService accountService = new AccountService();
	private static ClientService clientService = new ClientService();
	
	public static void createAccount(Context ctx) {
		ctx.status(201);
		Account accountFromRequestBody = ctx.bodyAsClass(Account.class);
		Account a = accountService.createAccount(accountFromRequestBody);
		ctx.json(a);
	}
	
	public static void getAllAccountsByClientId(Context ctx) {
		int clientId = Integer.parseInt(ctx.pathParam("client_id"));
		Client verifyId;
		try {
			verifyId = clientService.getClientById(clientId);
			if (verifyId.getId() == clientId) {
				List<Account> accounts = accountService.getAllAccountsByClientId(clientId);
				ctx.json(accounts);
			} else {
				ctx.status(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getSpecificAccountByClientId(Context ctx) {
		int clientId = Integer.parseInt(ctx.pathParam("client_id"));
		int accountNumber = Integer.parseInt(ctx.pathParam("account_number"));
		//Client verifyId;
		try {
			Client verifyId = clientService.getClientById(clientId);
			if (verifyId.getId() == clientId) {
				Account verifyAccount = accountService.getSpecificAccountByClientId(accountNumber, clientId);
				if (verifyAccount.getAccountNumber() == accountNumber) {
					verifyAccount = accountService.getSpecificAccountByClientId(accountNumber, clientId);
					ctx.json(verifyAccount);
				} else {
					ctx.status(404);
				}
			} else {
				ctx.status(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateAccount(Context ctx) {
		Account changeAccount = ctx.bodyAsClass(Account.class);
		System.out.println("changeAccount -= " + changeAccount);
		accountService.updateAccount(changeAccount);
	}
	public static void deleteAccount(Context ctx) {
		int accountNumber = Integer.parseInt(ctx.pathParam("account_number"));
		int clientId = Integer.parseInt(ctx.pathParam("client_id"));
		accountService.deleteAccount(accountNumber, clientId);
	}
}
