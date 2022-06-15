package dev.joshua_coombs.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.joshua_coombs.models.Account;
import dev.joshua_coombs.models.AlternateCALeftJoin;
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
	
	public List<ClientAccountLeftJoin> getAllAccountsByClientId(int clientId) {
		String sql = "select id, first_name, last_name, account_number, checking, savings"
				+ " from bankingapp.clients c"
				+ " left join bankingapp.accounts a"
				+ " on c.id = a.client_id"
				+ " where c.id = ?";
		List<ClientAccountLeftJoin> joined = new ArrayList<>();
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, clientId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				joined.add(new ClientAccountLeftJoin(
						rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getInt("account_number"),
						rs.getInt("checking"),
						rs.getInt("savings")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joined;
	}
	
	public ClientAccountLeftJoin getSpecificAccountByClientId(int clientId, int accountNumber) {
		String sql = "select id, first_name, last_name, account_number, checking, savings"
				+ " from bankingapp.clients c"
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
						rs.getInt("checking"),
						rs.getInt("savings"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joined;
	}
	
	public AlternateCALeftJoin getAccountsInValueRange(int clientId, String whichType, int low, int high) {
		if (whichType.equals("checking")) {
			String sqlChecking = "select id, first_name, last_name, account_number, checking from bankingapp.clients c "
					+ "left join bankingapp.accounts a "
					+ "on c.id = a.client_id "
					+ "where " + whichType + " > ? and " + whichType + " < ?;";
			AlternateCALeftJoin joinedChecking = null;
			try (Connection connChecking = cu.getConnection()) {
				PreparedStatement ps = connChecking.prepareStatement(sqlChecking);
				ps.setInt(1, low);
				ps.setInt(2, high);
				
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					joinedChecking = new AlternateCALeftJoin(
							rs.getInt("id"),
							rs.getString("first_name"),
							rs.getString("last_name"),
							rs.getInt("account_number"),
							rs.getInt("checking"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return joinedChecking;
		} else if (whichType.equals("savings")) {
			String sqlSavings = "select id, first_name, last_name, account_number, savings from bankingapp.clients c "
					+ "left join bankingapp.accounts a "
					+ "on c.id = a.client_id "
					+ "where " + whichType + " > ? and " + whichType + " < ?;";
			AlternateCALeftJoin joinedSavings = null;
			try (Connection connSavings = cu.getConnection()) {
				PreparedStatement ps = connSavings.prepareStatement(sqlSavings);
				ps.setInt(1, low);
				ps.setInt(2, high);
				
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					joinedSavings = new AlternateCALeftJoin(
							rs.getInt("id"),
							rs.getString("first_name"),
							rs.getString("last_name"),
							rs.getInt("account_number"),
							rs.getInt("savings"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return joinedSavings;
		}
		return null;
	}
	
	//needs to be fixed
	public boolean updateAccount(Account changeAccount) {
		String sql = "update bankingapp.accounts set checking = ?, savings = ?"
				+ " where account_number = ?"
				+ " and client_id = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, changeAccount.getCheckingAmount());
			ps.setInt(2, changeAccount.getSavingsAmount());
			ps.setInt(3, changeAccount.getAccountNumber());
			ps.setInt(4, changeAccount.getClientId());
			int updatedAccount = ps.executeUpdate();
			if (updatedAccount != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//not sure yet
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
		//String sql = "";
	}
	
	public void deposit() {
		//String sql = "";
	}
	
	public void transfer() {
		//String sql = "";
	}
}
