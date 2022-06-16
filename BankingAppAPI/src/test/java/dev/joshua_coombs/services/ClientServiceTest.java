package dev.joshua_coombs.services;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.joshua_coombs.repositories.ClientDAO;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
	@InjectMocks
	private static ClientService clientService;
	@Mock
	private static ClientDAO mockClientDao;
	
	@BeforeAll
	public static void setUp() {
		clientService = new ClientService(mockClientDao);
		mockClientDao = mock(ClientDAO.class);
	}
	
	@Test
	private static void createClientPositiveTest() {
		
	}
	
	@Test
	private static void createClientNegativeTest() {
		
	}
	
	@Test
	private static void getAllClientsPositiveTest() {
		
	}
	
	@Test
	private static void getAllClientsNegativeTest() {
		
	}
	
	@Test
	private static void getClientByIdPositiveTest() {
		
	}
	
	@Test
	private static void getClientByIdNegativeTest() {
		
	}
	
	@Test
	private static void updateClientPositiveTest() {
		
	}
	
	@Test
	private static void updateClientNegativeTest() {
		
	}
	
	@Test
	private static void deleteClientByIdPositiveTest() {
		
	}
	
	@Test
	private static void deleteClientByIdNegativeTest() {
		
	}
}
