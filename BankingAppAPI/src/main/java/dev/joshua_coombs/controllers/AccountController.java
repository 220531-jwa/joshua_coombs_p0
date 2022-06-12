package dev.joshua_coombs.controllers;

import dev.joshua_coombs.models.Account;
import dev.joshua_coombs.services.AccountService;
import io.javalin.http.Context;

public class AccountController {
	private static AccountService accountService = new AccountService();
	
	public static void createAccount(Context ctx) {
		ctx.status(201);
		Account accountFromRequestBody = ctx.bodyAsClass(Account.class);
		Account a = accountService.createAccount(accountFromRequestBody);
		ctx.json(a);
	}
	
	public static void getAllAccountsByClientId(Context ctx) {
		
	}
	
	public static void getSpecificAccountByClientId(Context ctx) {
		
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
