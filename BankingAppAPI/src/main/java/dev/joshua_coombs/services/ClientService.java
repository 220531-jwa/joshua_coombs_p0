/**
 * This ClientService class retrieves information from the ClientDAO class 
 * and is then accessed by the ClientController class
 * 
 * @author joshua_coombs
 * @version 1.0
 */

package dev.joshua_coombs.services;

import java.util.List;

import dev.joshua_coombs.models.Client;
import dev.joshua_coombs.repositories.ClientDAO;

public class ClientService {
	private static ClientDAO clientDao;
	
	public ClientService(ClientDAO clientDao) {
		this.clientDao = clientDao;
	}
	
	/**
	 * This method creates a client within the clients table of the database
	 * 
	 * @param c
	 * @return
	 */
	public Client createClient(Client c) {
		return clientDao.createClient(c);
	}
	
	/**
	 * This method gets all of the clients in the clients table of the
	 * database
	 * 
	 * @return
	 */
	public List<Client> getAllClients() {
		return clientDao.getAllClients();
	}
	
	/**
	 * This method gets a specific client by their id with the clients table 
	 * of the database. It throws a custom Exception which gives the
	 * HTTP status of 404 if no client by that id is found
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Client getClientById(int id) throws Exception {
		return clientDao.getClientById(id);
	}
	
	/**
	 * This method updates a specific client by their id from the clients 
	 * table within the database
	 * 
	 * @param changedClient
	 * @return
	 */
	public boolean updateClient(Client changedClient) {
		return clientDao.updateClientById(changedClient);
	}

	/**
	 * This method deletes a specific client by their id from the clients 
	 * table within the database
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteClientById(int id) {
		return clientDao.deleteClientById(id);
	}
}
