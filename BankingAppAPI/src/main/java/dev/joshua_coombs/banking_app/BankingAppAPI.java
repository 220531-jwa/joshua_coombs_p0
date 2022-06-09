package dev.joshua_coombs.banking_app;

import io.javalin.Javalin;

public class BankingAppAPI {

	public static void main(String[] args) {
		Javalin app = Javalin.create();
		app.start();
		app.post("/clients", ctx -> {
			//here
		});
		
		app.get("/clients", ctx -> {
			//here
		});
		
		app.get("/clients/10", ctx -> {
			//here
		});
		
		app.put("/clients/12", ctx -> {
			//here
		});
		
		app.delete("/clients/15", ctx -> {
			//here
		});
		
		app.post("/clients/5/accounts", ctx -> {
			//here
		});
		
		app.get("/clients/7/accounts", ctx -> {
			//here
		});
		
		app.get("/clients/7/accounts/accounts?amountLessThan=2000&amountGreaterThan400", ctx -> {
			//here
		});
		
		app.get("/clients/9/accounts/4", ctx -> {
			//here
		});
		
		app.put("/clients/10/accounts/3", ctx -> {
			//here
		});
		
		app.delete("/clients/15/accounts/6", ctx -> {
			//here
		});
		
		app.patch("/clients/17/accounts/12", ctx -> {
			//here
		});
		
		app.patch("/clients/17/accounts/7/transfer/8", ctx -> {
			//here
		});
	}

}