package dev.joshua_coombs.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.joshua_coombs.models.Client;
import dev.joshua_coombs.utils.ConnectionUtil;

public class ClientDAO {
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public Client createClient(Client c) {
		String sql = "insert into bankingapp.clients values (default, ?, ?) returning *";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getFirstName());
			ps.setString(2, c.getLastName());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c.setID(rs.getInt("id"));
				c.setFirstName(rs.getString("first_name"));
				c.setLastName(rs.getString("last_name"));
			} else {
				c = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public List<Client> getAllClients() {
		List<Client> clients = new ArrayList<>();
		String sql = "select * from bankingapp.clients";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				clients.add(new Client(
						rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}
	
	public Client getClientById(int id) {
		String sql = "select * from bankingapp.clients where id = ?";
		Client c = null;
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c = new Client(
						rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public boolean updateClientById (Client changeClient) {
		String sql = "update bankingapp.clients set (first_name, last_name)"
				+ " = (?, ?) where id = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, changeClient.getFirstName());
			ps.setString(2, changeClient.getLastName());
			ps.setInt(3, changeClient.getId());
			int updated = ps.executeUpdate();
			if (updated != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteClientById(int id) {
		String sql = "delete from bankingapp.clients where id = ?";
		boolean deletedClient = false;
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int ifDeleted = ps.executeUpdate();
			if (ifDeleted != 0) {
				deletedClient = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deletedClient;
	}
}
