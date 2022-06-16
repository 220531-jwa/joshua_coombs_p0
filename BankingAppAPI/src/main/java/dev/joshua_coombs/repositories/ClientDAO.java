/**
 * This ClientDAO class is a Data Access Object for the clients table within the database 
 * which is used in the ClientService and ClientController classes.
 * 
 * @author joshua_coombs
 * @version 1.0
 */

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
	
	/**
	 * This method creates a client within the clients table of the database
	 * 
	 * @param c
	 * @return
	 */
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
	
	/**
	 * This method gets all of the clients in the clients table of the
	 * database
	 * 
	 * @return
	 */
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
	
	/**
	 * This method gets a specific client by their id with the clients table 
	 * of the database
	 * 
	 * @param id
	 * @return
	 */
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
	
	/**
	 * This method updates a specific client by their id from the clients 
	 * table within the database
	 * 
	 * @param changeClient
	 * @return
	 */
	public boolean updateClientById(int id) {
		String sql = "update bankingapp.clients set (first_name, last_name)"
				+ " = (?, ?) where id = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "new");
			ps.setString(2, "name");
			ps.setInt(3, id);
			int updated = ps.executeUpdate();
			if (updated != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * This method deletes a specific client by their id from the clients 
	 * table within the database
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteClientById(int id) {
		String sql = "delete from bankingapp.clients where id = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int ifDeleted = ps.executeUpdate();
			if (ifDeleted != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
