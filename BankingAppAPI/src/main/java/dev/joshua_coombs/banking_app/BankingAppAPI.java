/**
 * This BankingAppAPI performs what the README.md file specifies
 * 
 * @author joshua_coombs
 * @version 1.0
 */

package dev.joshua_coombs.banking_app;

import dev.joshua_coombs.controllers.AccountController;
import dev.joshua_coombs.controllers.ClientController;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class BankingAppAPI {
	
	/**
	 * This method is the entry point for the application
	 * 
	 * @param args
	 */
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
							put(AccountController::updateAccount); //works
							delete(AccountController::deleteAccount); //works
							path("/{which_type_dw}", () -> {
								path("/deposit/{amount_d}", () -> {
									patch(AccountController::deposit); //works
								});
								path("/withdraw/{amount_w}", () -> {
									patch(AccountController::withdraw); //works
								});
							});
							path("/{which_type_tf}/transfer/{other_account}/{which_type_tt}/{amount}", () -> {
								patch(AccountController::transfer); //works
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
	}

}