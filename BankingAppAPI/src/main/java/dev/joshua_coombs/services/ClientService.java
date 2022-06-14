package dev.joshua_coombs.services;

import java.util.List;

import dev.joshua_coombs.models.Client;
import dev.joshua_coombs.repositories.ClientDAO;

public class ClientService {
	private static ClientDAO clientDao = new ClientDAO();
	
	public Client createClient(Client c) {
		return clientDao.createClient(c);
	}
	
	public List<Client> getAllClients() {
		return clientDao.getAllClients();
	}
	
	public Client getClientById(int id) throws Exception {
		return clientDao.getClientById(id);
	}
	
	public boolean deleteClientById(int id) {
		return clientDao.deleteClientById(id);
	}
	
	public boolean updateClient(Client changedClient) {
		return clientDao.updateClientById(changedClient);
	}
}
