/**
 * This AccountController class handles methods defined within the AccountService
 * class. The methods of this class are accessed by the main method of the 
 * BankingAppAPI class.
 * 
 * @author joshua_coombs
 * @version 1.0
 */

package dev.joshua_coombs.controllers;

import java.util.List;

import dev.joshua_coombs.models.Account;
import dev.joshua_coombs.models.AlternateCALeftJoin;
import dev.joshua_coombs.models.Client;
import dev.joshua_coombs.models.ClientAccountLeftJoin;
import dev.joshua_coombs.repositories.AccountDAO;
import dev.joshua_coombs.services.AccountService;
import dev.joshua_coombs.services.ClientService;
import io.javalin.http.Context;

public class AccountController {
	private static AccountService accountService = new AccountService();
	private static ClientService clientService = new ClientService();
	
	/**
	 * This method creates an account within the accounts table of
	 * the database which is associated with a specific client id and
	 * then displays the account
	 * 
	 * @param ctx
	 */
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
	
	/**
	 * This method gets all the accounts associated with a specific client id
	 * within the database
	 * 
	 * @param ctx
	 */
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
	
	/**
	 * This method gets a specific account associated with a specific client id
	 * within the database
	 * 
	 * @param ctx
	 */
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
	
	/**
	 * This method gets all the accounts associated with a specific client id
	 * which abide by a certain value range
	 * 
	 * @param ctx
	 */
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
	
	/**
	 * This method updates a specific account associated with a specific client id
	 * 
	 * @param ctx
	 */
	public static void updateAccount(Context ctx) {
		int accountNumber = Integer.parseInt(ctx.pathParam("account_number"));
		int clientId = Integer.parseInt(ctx.pathParam("id"));
		AccountDAO ad = new AccountDAO();
		int checkingAmount = ad.getSpecificAccountByClientId(clientId, accountNumber).getCheckingAmount() + 200;
		int savingsAmount = ad.getSpecificAccountByClientId(clientId, accountNumber).getSavingsAmount() + 200;
		boolean updated = accountService.updateAccount(clientId, accountNumber, checkingAmount, savingsAmount);
		if (!updated) {
			ctx.status(404);
		} else {
			ctx.status(200);
		}
	}

	/**
	 * This method withdraws a certain amount from either a checking or savings
	 * sub-account, within a specific account associated with a client id, and
	 * displays the details of the account after the withdrawal
	 * 
	 * @param ctx
	 */
	public static void withdraw(Context ctx) {
		int accountNumber = Integer.parseInt(ctx.pathParam("account_number"));
		int clientId = Integer.parseInt(ctx.pathParam("id"));
		String whichType = ctx.pathParam("which_type_dw");
		int amountToWithdraw = Integer.parseInt(ctx.pathParam("amount_w"));
		boolean withdrawn = accountService.withdraw(clientId, accountNumber, whichType, amountToWithdraw);
		try {
			ClientAccountLeftJoin aBefore = accountService.getSpecificAccountByClientId(clientId, accountNumber);
			ctx.json(aBefore);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			ClientAccountLeftJoin aAfter = accountService.getSpecificAccountByClientId(clientId, accountNumber);
			if (!withdrawn) {
				ctx.status(422);
			} else {
				ctx.status(200);
				ctx.json(aAfter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method deposits a certain amount into either a checking or savings
	 * sub-account, within a specific account associated with a client id, and
	 * displays the details of the account after the deposit
	 * 
	 * @param ctx
	 */
	public static void deposit(Context ctx) {
		int accountNumber = Integer.parseInt(ctx.pathParam("account_number"));
		int clientId = Integer.parseInt(ctx.pathParam("id"));
		String whichType = ctx.pathParam("which_type_dw");
		int amountToDeposit = Integer.parseInt(ctx.pathParam("amount_d"));
		boolean deposited = accountService.deposit(clientId, accountNumber, whichType, amountToDeposit);
		try {
			ClientAccountLeftJoin aBefore = accountService.getSpecificAccountByClientId(clientId, accountNumber);
			ctx.json(aBefore);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			ClientAccountLeftJoin aAfter = accountService.getSpecificAccountByClientId(clientId, accountNumber);
			if (!deposited) {
				ctx.status(422);
			} else {
				ctx.status(200);
				ctx.json(aAfter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method transfers a certain amount from one account (either 
	 * checking or savings) and to another account (either checking or 
	 * savings), both of which are associated with a specific client id
	 * 
	 * @param ctx
	 */
	public static void transfer(Context ctx) {
		int fromAccount = Integer.parseInt(ctx.pathParam("account_number"));
		int toAccount = Integer.parseInt(ctx.pathParam("other_account"));
		int clientId = Integer.parseInt(ctx.pathParam("id"));
		String fromWhichType = ctx.pathParam("which_type_tf");
		String toWhichType = ctx.pathParam("which_type_tt");
		int amount = Integer.parseInt(ctx.pathParam("amount"));
		try {
			ClientAccountLeftJoin aFromBefore = accountService.getSpecificAccountByClientId(clientId, fromAccount);
			ClientAccountLeftJoin aToBefore = accountService.getSpecificAccountByClientId(clientId, toAccount);
			ctx.json(aFromBefore);
			ctx.json(aToBefore);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		boolean transferred = accountService.transfer(clientId, fromAccount, toAccount, fromWhichType, toWhichType, amount);
		try {
			ClientAccountLeftJoin aFromAfter = accountService.getSpecificAccountByClientId(clientId, fromAccount);
			ClientAccountLeftJoin aToAfter = accountService.getSpecificAccountByClientId(clientId, toAccount);
			if (!transferred) {
				ctx.status(422);
			} else {
				ctx.status(200);
				ctx.json(aFromAfter);
				ctx.json(aToAfter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This methods deletes a specific account associated with a specific client id
	 * 
	 * @param ctx
	 */
	public static void deleteAccount(Context ctx) {
		int accountNumber = Integer.parseInt(ctx.pathParam("account_number"));
		int clientId = Integer.parseInt(ctx.pathParam("id"));
		boolean deletedAccount = accountService.deleteAccount(clientId, accountNumber);
		if (!deletedAccount) {
			ctx.status(404);
		} else {
			ctx.status(205);
		}
	}
}
