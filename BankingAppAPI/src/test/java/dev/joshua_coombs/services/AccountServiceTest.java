package dev.joshua_coombs.services;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.joshua_coombs.repositories.AccountDAO;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	@InjectMocks
	private static AccountService accountService;
	@Mock
	private static AccountDAO mockAccountDao;
	
	@BeforeAll
	public static void setUp() {
		accountService = new AccountService(mockAccountDao);
		mockAccountDao = mock(AccountDAO.class);
	}
}
