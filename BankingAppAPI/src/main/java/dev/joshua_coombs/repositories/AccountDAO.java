package dev.joshua_coombs.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.joshua_coombs.models.Account;
import dev.joshua_coombs.utils.ConnectionUtil;

public class AccountDAO {
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public Account createAccount (Account a) {
		String sql = "insert into accounts values (default, ?, ?, ?) returning *";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getClientId());
			ps.setInt(2, a.getCheckingAmount());
			ps.setInt(3, a.getSavingsAmount());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return new Account(
						rs.getInt("account_number"),
						rs.getInt("client_id"),
						rs.getInt("checking"),
						rs.getInt("savings"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Account> getAllAccountsByClientId(int clientIdParam) {
		List<Account> accounts = new ArrayList<>();
		String sql = "select * from accounts with client_id = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, clientIdParam);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int accountNumber = rs.getInt("account_number");
				int clientId = rs.getInt("client_id");
				int checkingAmount = rs.getInt("checking");
				int savingsAmount = rs.getInt("savings");
				Account a = new Account(accountNumber, clientId, checkingAmount, savingsAmount);
				accounts.add(a);
			}
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Account getSpecificAccountByClientId(int accountNumber, int clientId) {
		String sql = "select from accounts where client_id = ? "
				+ "and account_number = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, clientId);
			ps.setInt(2, accountNumber);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return new Account(
						rs.getInt("account_number"),
						rs.getInt("client_id"),
						rs.getInt("checking"),
						rs.getInt("savings"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateAccount(Account changeAccount) {
		String sql = "update accounts set checking = ?, savings = ? where account_number = ? "
				+ "and client_id = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, changeAccount.getCheckingAmount());
			ps.setInt(2, changeAccount.getSavingsAmount());
			ps.setInt(3, changeAccount.getAccountNumber());
			ps.setInt(4, changeAccount.getClientId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAccount(int accountNumber, int clientId) {
		String sql = "delete from accounts where account_number = ? "
				+ "client_id = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accountNumber);
			ps.setInt(2, clientId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
