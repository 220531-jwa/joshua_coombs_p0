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
						path("/{account_number}", () -> {
							get(AccountController::getSpecificAccountByClientId); //works
							path("/{which_type}", () -> {
								get(AccountController::getAccountsInValueRange);
							});
							put(AccountController::updateAccount); //doesn't work
							//update account with the id 3 for client 10 return 404 if no account or client exists
							delete(AccountController::deleteAccount); //not sure yet
							//delete account 6 for client 15 return 404 if no account or client exists
							patch(AccountController::withdrawFromAccount); //need to implement
							patch(AccountController::depositIntoAccount); //need to implement
							path("/transfer/{other_account}", () -> {
								patch(AccountController::transferFunds); //need to implement
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