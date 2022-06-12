package dev.joshua_coombs.services;

import java.util.List;

import dev.joshua_coombs.models.Client;
import dev.joshua_coombs.repositories.ClientDAO;

public class ClientService {
	private static ClientDAO clientDao = new ClientDAO();
	
	public Client createClient(Client c) {
		Client newClient = clientDao.createClient(c);
		return newClient;
	}
	
	/*
	public Client createClientWithId(int n) {
		Client newClient = clientDao.createClientById(n);
		return newClient;
	}
	*/
	
	public List<Client> getAllClients() {
		return clientDao.getAllClients();
	}
	
	public Client getClientById(int id) throws Exception {
		Client c = clientDao.getClientById(id);
		if (c == null) {
			throw new Exception("Client not found");
		}
		return c;
	}
	
	public void deleteClientById(int id) {
		clientDao.deleteClientById(id);
	}
	
	public void updateClient(Client changedClient) {
		clientDao.updateClient(changedClient);
	}
}
