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
	//private List<Client> clients = BankDB.clients;
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public Client createClient(Client c) {
		String sql = "insert into clients values (default, ?, ?) returning *";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getFirstName());
			ps.setString(2, c.getLastName());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Client(
						rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	public Client createClientById(int id) {
		Client client = new Client(id);
		return client;
	}
	*/
	
	public List<Client> getAllClients() {
		List<Client> clients = new ArrayList<>();
		String sql = "select * from clients";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Client c = new Client(id, firstName, lastName);
				clients.add(c);
			}
			return clients;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Client getClientById(int id) {
		String sql = "select * from clients where id = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Client c = new Client(
						rs.getInt(id),
						rs.getString("first_name"),
						rs.getString("last_name"));
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateClient (Client changeClient) {
		String sql = "update clients set first_name = ?, last_name = ? where id = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, changeClient.getFirstName());
			ps.setString(2, changeClient.getLastName());
			ps.setInt(3, changeClient.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteClientById(int id) {
		String sql = "delete from clients where id = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
