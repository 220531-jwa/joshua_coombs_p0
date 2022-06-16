/**
 * This ConnectionUtil class is serves as the connection point between this
 * Java application and the database and it is used by both the ClientDAO and
 * AccountDAO classes.
 * 
 * @author joshua_coombs
 * @version 1.0
 */

package dev.joshua_coombs.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	private static ConnectionUtil cu;
	private static Properties dbProps;
	
	/**
	 * This constructor sets up the ConnectionUtil object with the appropriate
	 * credentials to connect to the database
	 */
	private ConnectionUtil() {
		dbProps = new Properties();
		InputStream props = ConnectionUtil.class.getClassLoader().getResourceAsStream("connection.properties");
		try {
			dbProps.load(props);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks to make sure a connection is able to be made to the database
	 * 
	 * @return
	 */
	public static synchronized ConnectionUtil getConnectionUtil() {
		if (cu == null) {
			cu = new ConnectionUtil();
		}
		return cu;
	}
	
	/**
	 * This method actually makes the connection to the database
	 * 
	 * @return
	 */
	public Connection getConnection() {
		Connection cnct = null;
		
		String url = dbProps.getProperty("url");
		String username = dbProps.getProperty("username");
		String password = dbProps.getProperty("password");
		try {
			cnct = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnct;
	}
}
