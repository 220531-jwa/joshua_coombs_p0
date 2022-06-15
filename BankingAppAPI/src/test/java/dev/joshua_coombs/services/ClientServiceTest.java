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
	
	
}
