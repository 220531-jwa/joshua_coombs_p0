package dev.joshua_coombs.banking_app;

import java.util.List;

import dev.joshua_coombs.models.Client;
import dev.joshua_coombs.repositories.ClientDAO;
import dev.joshua_coombs.services.ClientService;
import dev.joshua_coombs.controllers.AccountController;
import dev.joshua_coombs.controllers.ClientController;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class BankingAppAPI {
	private static ClientService clientService;
	
	public static void main(String[] args) {
		clientService = new ClientService();
		Javalin app = Javalin.create();
		app.start();
		
		app.routes(() -> {
			path("/clients", () -> {
				get(ClientController::getAllClients);
				post(ClientController::createClient);
				path("/{id}", () -> {
					get(ClientController::getClientById);
					post(ClientController::createClientById);
					put(ClientController::updateClientById); //implement
					delete(ClientController::deleteClientById);
					path("/accounts", () -> {
						post(AccountController::createNewAccount); //implement
						//creates a new account for client with the id of 5 return a 201 status code
						get(AccountController::getAllAccounts); //implement
						//get all accounts for client 7 return 404 if no client exists
						path("/{id}", () -> {
							get(AccountController::getSpecificAccount); //implement
							//get account 4 for client 9 return 404 if no account or client exists
							post(AccountController::createAccountById); //implement
							//
							put(AccountController::updateAccountById); //implement
							//update account with the id 3 for client 10 return 404 if no account or client exists
							delete(AccountController::deleteAccountById);
							//delete account 6 for client 15 return 404 if no account or client exists
							path("/transfer/{id}", () -> {
								
							});
						});
					});
				});
			});
		});
		
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
	}

}