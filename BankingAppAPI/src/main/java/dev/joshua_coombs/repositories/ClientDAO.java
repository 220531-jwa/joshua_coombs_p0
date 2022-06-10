package dev.joshua_coombs.repositories;

import java.util.List;

import dev.joshua_coombs.models.Client;
import dev.joshua_coombs.utils.BankDB;

public class ClientDAO {
	private List<Client> clients = BankDB.clients;
	public Client createClient(Client c) {
		BankDB.clients.add(c);
		return c;
	}
	
	public Client createClientById(int id) {
		Client client = new Client(id);
		return client;
	}
	
	public List<Client> getAllClients() {
		return clients;
	}
	
	public Client getClientById(int id) {
		return clients.get(id-1);
	}
	
	public void deleteClientById(int id) {
		for (Client c : clients) {
			if (c.getId() == id) {
				clients.remove(c);
			}
		}
	}
}
