package dev.joshua_coombs.controllers;

import java.util.List;

import dev.joshua_coombs.models.Client;
import dev.joshua_coombs.services.ClientService;
import io.javalin.http.Context;

public class ClientController {
	private static ClientService clientService = new ClientService();
	private static List<Client> clients = clientService.getAllClients();
	public static void getAllClients(Context ctx) {
		ctx.status(200);
		clients = clientService.getAllClients();
		ctx.json(clients);
	}
	
	public static void createClient(Context ctx) {
		ctx.status(201);
		Client clientFromRequestBody = ctx.bodyAsClass(Client.class);
		Client c = clientService.createClient(clientFromRequestBody);
		ctx.json(c);
	}
	
	public static void getClientById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Client c = null;
		try {
			c = clientService.getClientById(id);
		} catch (Exception e) {
			ctx.status(404);
			e.printStackTrace();
		}
		ctx.status(200);
		ctx.json(c);
	}
	
	/*
	public static void createClientById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		for (Client c : clients) {
			if (c.getId() == id) {
				ctx.status(404);
			} else {
				Client client = clientService.createClientWithId(id);
				ctx.status(201);
				ctx.json(client);
			}
		}
	}
	*/
	
	public static void deleteClientById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		clientService.deleteClientById(id);
		/*(
		int id = Integer.parseInt(ctx.pathParam("id"));
		Client c = clientService.getClientById(id);
		if (c.getId() == id) {
			clientService.deleteClientById(id);
			ctx.status(205);
			//ctx.json(clientService.deleteClientById(id));
		} else {
			ctx.status(404);
		}
		*/
	}
	
	public static void updateClientById(Context ctx) {
		Client changedClient = ctx.bodyAsClass(Client.class);
		System.out.println("updateClient -=" + changedClient);
		clientService.updateClient(changedClient);
	}
}
