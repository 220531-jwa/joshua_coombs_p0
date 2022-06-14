package dev.joshua_coombs.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.joshua_coombs.models.Account;
import dev.joshua_coombs.models.ClientAccountLeftJoin;
import dev.joshua_coombs.utils.ConnectionUtil;

public class AccountDAO {
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public Account createAccount (Account a) {
		String sql = "insert into bankingapp.accounts values (default, ?, ?, ?) returning *";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getClientId());
			ps.setInt(2, a.getCheckingAmount());
			ps.setInt(3, a.getSavingsAmount());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				a.setAccountNumber(rs.getInt("account_number"));
				a.setClientId(rs.getInt("client_id"));
				a.setCheckingAmount(rs.getInt("checking"));
				a.setSavingsAmount(rs.getInt("savings"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public ClientAccountLeftJoin getAllAccountsByClientId(int clientId) {
		String sql = "select * from bankingapp.clients c"
				+ " left join bankingapp.accounts a"
				+ " on c.id = a.client_id"
				+ " where c.id = ?";
		ClientAccountLeftJoin joined = null;
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, clientId);
			ResultSet rs = ps.executeQuery();
			
			//if may increment before
			if (rs.next()) {
				joined = new ClientAccountLeftJoin(
						rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getInt("account_number"),
						rs.getInt("client_id"),
						rs.getInt("checking"),
						rs.getInt("savings"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joined;
	}
	
	public ClientAccountLeftJoin getSpecificAccountByClientId(int clientId, int accountNumber) {
		String sql = "select * from bankingapp.clients c"
				+ " left join bankingapp.accounts a"
				+ " on c.id = a.client_id"
				+ " where c.id = ? and a.account_number = ?";
		ClientAccountLeftJoin joined = null;
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, clientId);
			ps.setInt(2, accountNumber);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				joined = new ClientAccountLeftJoin(
						rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getInt("account_number"),
						rs.getInt("client_id"),
						rs.getInt("checking"),
						rs.getInt("savings"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joined;
	}
	
	public boolean updateAccount(Account changeAccount) {
		String sqlInitial = "update bankingapp.accounts set checking = ?, savings = ?"
				+ " where account_number = ?"
				+ " and client_id = ?";
		Account a = null;
		try (Connection connInitial = cu.getConnection()) {
			PreparedStatement psInitial = connInitial.prepareStatement(sqlInitial);
			psInitial.setInt(1, changeAccount.getCheckingAmount());
			psInitial.setInt(2, changeAccount.getSavingsAmount());
			psInitial.setInt(3, changeAccount.getAccountNumber());
			psInitial.setInt(4, changeAccount.getClientId());
			int updatedInitial = psInitial.executeUpdate();
			if (updatedInitial != 0) {
				String sqlSubsequent = "select * from bankingapp.clients c"
						+ " left join bankingapp.accounts a"
						+ " on c.id = a.client_id"
						+ " where c.id = ? and a.account_number = ?";
				try (Connection connSubsequent = cu.getConnection()) {
					PreparedStatement psSubsequent = connSubsequent.prepareStatement(sqlSubsequent);
					psSubsequent.setInt(1, changeAccount.getClientId());
					psSubsequent.setInt(2, changeAccount.getAccountNumber());
					int updatedSubsequent = psSubsequent.executeUpdate();
					if (updatedSubsequent != 0) {
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteAccount(int clientId, int accountNumber) {
		String sql = "delete from bankingapp.accounts where account_number = ? "
				+ "client_id = ?";
		boolean deletedAccount = false;
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accountNumber);
			ps.setInt(2, clientId);
			int ifDeleted = ps.executeUpdate();
			if (ifDeleted != 0) {
				deletedAccount = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deletedAccount;
	}
	
	public void withdraw() {
		String sql = "";
	}
	
	public void deposit() {
		String sql = "";
	}
	
	public void transfer() {
		String sql = "";
	}
}
