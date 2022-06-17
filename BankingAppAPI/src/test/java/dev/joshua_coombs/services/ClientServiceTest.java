package dev.joshua_coombs.services;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.joshua_coombs.models.Client;
import dev.joshua_coombs.repositories.ClientDAO;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
	@InjectMocks
	private static ClientService clientService;
	@Mock
	private static ClientDAO mockClientDao;
	
	@Test
	private static void createClientPositiveTest() {
		Client mockClient = new Client(5, "arbitrary", "immaterial");
		assertEquals(when(mockClientDao.createClient(mockClient)).thenReturn(mockClient), 
				clientService.createClient(mockClient));
	}
	
	@Test
	private static void getAllClientsPositiveTest() {
		List<Client> mockClients = new ArrayList<>();
		mockClients.add(new Client(2, "robert", "frost"));
		mockClients.add(new Client(3, "edgar", "poe"));
		mockClients.add(new Client(4, "shallow", "grave"));
		mockClients.add(new Client(5, "arbitrary", "immaterial"));
		assertEquals(when(mockClientDao.getAllClients()).thenReturn(mockClients), 
				clientService.getAllClients());
	}
	
	@Test
	private static void getClientByIdPositiveTest() throws Exception {
		Client mockClient = new Client(2, "robert", "frost");
		assertEquals(when(mockClientDao.getClientById(2)).thenReturn(mockClient), 
				clientService.getClientById(2));
	}
	
	@Test
	private static void updateClientPositiveTest() {
		int id = 5;
		assertEquals(when(mockClientDao.updateClientById(id)).thenReturn(true), 
				clientService.updateClient(id));
	}
	
	@Test
	private static void deleteClientByIdPositiveTest() {
		int id = 5;
		assertEquals(when(mockClientDao.deleteClientById(id)).thenReturn(true), 
				clientService.deleteClientById(id));
	}
}
