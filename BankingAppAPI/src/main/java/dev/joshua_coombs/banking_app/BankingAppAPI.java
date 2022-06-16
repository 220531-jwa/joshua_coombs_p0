package dev.joshua_coombs.banking_app;

//import java.util.List;

import dev.joshua_coombs.controllers.AccountController;
import dev.joshua_coombs.controllers.ClientController;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class BankingAppAPI {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create();
		app.start();
		
		app.routes(() -> {
			path("/clients", () -> {
				get(ClientController::getAllClients); //works
				post(ClientController::createClient); //works
				path("/{id}", () -> {
					get(ClientController::getClientById); //works
					put(ClientController::updateClientById); //works
					delete(ClientController::deleteClientById); //works
					path("/accounts", () -> {
						post(AccountController::createAccount); //works
						get(AccountController::getAllAccountsByClientId); //works
						path("/{which_type}", () -> {
							get(AccountController::getAccountsInValueRange); //works
							
						});
						path("/{account_number}", () -> {
							get(AccountController::getSpecificAccountByClientId); //works
							put(AccountController::updateAccount); //doesn't work yet
							delete(AccountController::deleteAccount); //works
							path("/{which_type_dw}", () -> {
								path("/deposit/{amount_d}", () -> {
									patch(AccountController::deposit); //works
								});
								path("/withdraw/{amount_w}", () -> {
									patch(AccountController::withdraw); //works
								});
							});
							path("/{which_type_tf}/transfer/{other_account}/{which_type_tt}/{amount_one}/{amount_two}", () -> {
								patch(AccountController::transfer); //doesn't work yet
							});
						});
					});
				});
			});
		});
		
		app.exception(Exception.class, (e, ctx) -> {
			ctx.status(404);
			ctx.result("Not found");
		});
		
		/*
		app.get("/clients/7/accounts/accounts?amountLessThan=2000&amountGreaterThan400", ctx -> {
			//get all accounts for client 7 between 400 and 2000 return 404 if no client exists
		});
		
		
		app.delete("/clients/15/accounts/6", ctx -> {
			//delete account 6 for client 15 return 404 if no account or client exists
		});
		
		app.patch("/clients/17/accounts/12", ctx -> {
			//Withdraw/deposit given amount (Body: {"deposit":500} or {"withdraw":250} return 404  
			//if no account or client exists return 422 if insufficient funds
		});
		
		app.patch("/clients/17/accounts/7/transfer/8", ctx -> {
			//transfer funds from account 7 to account 8 (Body: {"amount":500}) return 404 if no  
			//client or either account exists return 422 if insufficient funds
		});
		*/
	}

}