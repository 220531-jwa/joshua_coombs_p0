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
		Client mockClient = new Client(1, "arbitrary", "immaterial");
		assertEquals(when(mockClientDao.createClient(mockClient)).thenReturn(mockClient), 
				clientService.createClient(mockClient));
	}
	
	@Test
	private static void getAllClientsPositiveTest() {
		List<Client> mockClients = new ArrayList<>();
		mockClients.add(new Client(1, "abc", "def"));
		mockClients.add(new Client(2, "ghi", "jkl"));
		mockClients.add(new Client(3, "mno", "pqr"));
		mockClients.add(new Client(4, "stu", "vwxyz"));
		assertEquals(when(mockClientDao.getAllClients()).thenReturn(mockClients), 
				clientService.getAllClients());
	}
	
	@Test
	private static void getClientByIdPositiveTest() throws Exception {
		Client mockClient = new Client(10, "arbitrary", "immaterial");
		assertEquals(when(mockClientDao.getClientById(10)).thenReturn(mockClient), 
				clientService.getClientById(10));
	}
	
	@Test
	private static void updateClientPositiveTest() {
		int id = 2;
		assertEquals(when(mockClientDao.updateClientById(id)).thenReturn(true), 
				clientService.updateClient(id));
	}
	
	@Test
	private static void deleteClientByIdPositiveTest() {
		Client mockClient = new Client(15, "arbitrary", "immaterial");
		assertEquals(when(mockClientDao.deleteClientById(mockClient.getId())).thenReturn(true), 
				clientService.deleteClientById(15));
	}
}
