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
	
	@Test
	public static void createAccountPositiveTest() {
		
	}
	
	@Test
	public static void createAccounNegativetTest() {
		
	}
	
	@Test
	public static void getAllAccountsByClientIdPositiveTest() {
		
	}
	
	@Test
	public static void getAllAccountsByClientIdNegativeTest() {
		
	}
	
	@Test
	public static void getSpecificAccountByClientIdPositiveTest() {
		
	}
	
	@Test
	public static void getSpecificAccountByClientIdNegativeTest() {
		
	}
	
	@Test
	public static void getAccountsInValueRangePositiveTest() {
		
	}
	
	@Test
	public static void getAccountsInValueRangeNegativeTest() {
		
	}
	
	@Test
	public static void updateAccountPositiveTest() {
		
	}
	
	@Test
	public static void updateAccountNegativeTest() {
		
	}
	
	@Test
	public static void withdrawPositiveTest() {
		
	}
	
	@Test
	public static void withdrawNegativeTest() {
		
	}
	
	@Test
	public static void depositPositiveTest() {
		
	}
	
	@Test
	public static void depositNegativeTest() {
		
	}
	
	@Test
	public static void transferPositiveTest() {
		
	}
	
	@Test
	public static void transferNegativeTest() {
		
	}
	
	@Test
	public static void deleteAccountPositiveTest() {
		
	}
	
	@Test
	public static void deleteAccountNegativeTest() {
		
	}
}
