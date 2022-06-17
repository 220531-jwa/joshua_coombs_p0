package dev.joshua_coombs.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.joshua_coombs.models.Account;
import dev.joshua_coombs.models.AlternateCALeftJoin;
import dev.joshua_coombs.models.ClientAccountLeftJoin;
import dev.joshua_coombs.repositories.AccountDAO;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	@InjectMocks
	private static AccountService accountService;
	@Mock
	private static AccountDAO mockAccountDao;
	
	@Test
	public static void createAccountPositiveTest() {
		Account mockAccount = new Account(10, 2, 500, 500);
		assertEquals(when(mockAccountDao.createAccount(mockAccount)).thenReturn(mockAccount), 
				accountService.createAccount(mockAccount));
	}
	
	@Test
	public static void getAllAccountsByClientIdPositiveTest() {
		List<ClientAccountLeftJoin> mockCA = new ArrayList<>();
		mockCA.add(new ClientAccountLeftJoin(3, "edgar", "poe", 3, 200, 200));
		mockCA.add(new ClientAccountLeftJoin(3, "edgar", "poe", 6, 401, 1999));
		mockCA.add(new ClientAccountLeftJoin(3, "edgar", "poe", 7, 500, 500));
		assertEquals(when(mockAccountDao.getAllAccountsByClientId(3)).thenReturn(mockCA), 
				accountService.getAllAccountsByClientId(3));
	}
	
	@Test
	public static void getSpecificAccountByClientIdPositiveTest() throws Exception {
		int clientId = 2;
		int accountNumber = 2;
		ClientAccountLeftJoin ca = new ClientAccountLeftJoin(
				clientId, "robert", "frost", accountNumber, 200, 7500);
		assertEquals(when(mockAccountDao.getSpecificAccountByClientId(clientId, accountNumber)).thenReturn(ca), 
				accountService.getSpecificAccountByClientId(clientId, accountNumber));
	}
	
	@Test
	public static void getAccountsInValueRangeCheckingPositiveTest() {
		int clientId = 2;
		String whichType = "checking";
		int low = 400;
		int high = 2000;
		assertEquals(when(mockAccountDao.getAccountsInValueRange(clientId, whichType, low, high)), 
				accountService.getAccountsInValueRange(clientId, whichType, low, high));
	}
	
	@Test
	public static void updateAccountPositiveTest() {
		int clientId = 4;
		int accountNumber = 4;
		int checkingAmount = 455;
		int savingsAmount = 554;
		assertEquals(when(mockAccountDao.updateAccount(clientId, accountNumber, checkingAmount, savingsAmount)), 
				accountService.updateAccount(clientId, accountNumber, checkingAmount, savingsAmount));
	}
	
	@Test
	public static void withdrawPositiveTest() {
		int clientId = 4; 
		int accountNumber = 4;
		String whichType = "checking";
		int amountToWithdraw = 5;
		assertEquals(when(mockAccountDao.withdraw(clientId, accountNumber, whichType, amountToWithdraw)), 
				accountService.withdraw(clientId, accountNumber, whichType, amountToWithdraw));
	}
	
	@Test
	public static void depositPositiveTest() {
		int clientId = 4; 
		int accountNumber = 4;
		String whichType = "checking";
		int amountToDeposit = 5;
		assertEquals(when(mockAccountDao.withdraw(clientId, accountNumber, whichType, amountToDeposit)), 
				accountService.withdraw(clientId, accountNumber, whichType, amountToDeposit));
	}
	
	@Test
	public static void transferPositiveTest() {
		int clientId = 4;
		int fromAccount = 4;
		int toAccount = 5;
		String fromWhichType = "checking";
		String toWhichType = "checking";
		int amount = 45;
		assertEquals(when(mockAccountDao.transfer(clientId, fromAccount, toAccount, fromWhichType, toWhichType, amount)), 
				accountService.transfer(clientId, fromAccount, toAccount, fromWhichType, toWhichType, amount));
	}
	
	@Test
	public static void deleteAccountPositiveTest() {
		int clientId = 4;
		int accountNumber = 9;
		assertEquals(when(mockAccountDao.deleteAccount(clientId, accountNumber)), 
				accountService.deleteAccount(clientId, accountNumber));
	}
}
