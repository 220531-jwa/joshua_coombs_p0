/**
 * This ClientController class handles methods defined within the ClientService
 * class. The methods  of this class are accessed by the main method of the 
 * BankingAppAPI class
 * 
 * @author joshua_coombs
 * @version 1.0
 */

package dev.joshua_coombs.controllers;

import java.util.List;

import dev.joshua_coombs.models.Client;
import dev.joshua_coombs.services.ClientService;
import io.javalin.http.Context;

public class ClientController {
	private static ClientService clientService = new ClientService();
	private static List<Client> clients = clientService.getAllClients();
	
	/**
	 * This method creates a client within the clients table of the database
	 * 
	 * @param ctx
	 */
	public static void createClient(Context ctx) {
		Client clientFromRequestBody = ctx.bodyAsClass(Client.class);
		Client c = clientService.createClient(clientFromRequestBody);
		
		if (c == null) {
			ctx.status(422);
		} else {
			ctx.status(201);
			ctx.json(c);
		}
	}
	
	/**
	 * This method gets all of the clients in the clients table of the
	 * database
	 * 
	 * @param ctx
	 */
	public static void getAllClients(Context ctx) {
		if (clients.size() > 0) {
			ctx.status(200);
			ctx.json(clients);
		} else {
			ctx.status(404);
		}
	}
	
	/**
	 * This method gets a specific client by their id with the clients table 
	 * of the database
	 * 
	 * @param ctx
	 */
	public static void getClientById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Client c = null;
		try {
			c = clientService.getClientById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ctx.status(200);
		ctx.json(c);
	}
	
	/**
	 * This method updates a specific client by their id from the clients 
	 * table within the database
	 * 
	 * @param ctx
	 */
	public static void updateClientById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		boolean updated = clientService.updateClient(id);
		if (!updated) {
			ctx.status(404);
		} else {
			ctx.status(200);
		}
	}
	
	/**
	 * This method deletes a specific client by their id from the clients 
	 * table within the database
	 * 
	 * @param ctx
	 */
	public static void deleteClientById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deletedClient = clientService.deleteClientById(id);
        if (!deletedClient) {
            ctx.status(404);
        } else {
           ctx.status(205);
        }
    }
}
