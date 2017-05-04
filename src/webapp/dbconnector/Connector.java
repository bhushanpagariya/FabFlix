package webapp.dbconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	private Connection connection;
	private String DB_USERNAME = "root";
	private String DB_PASSWORD = "bhushan";
	
	public void createConnection(){
		try {
			// Incorporate mySQL driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Create connection
			this.setConnection(DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",this.DB_USERNAME, this.DB_PASSWORD));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void closeConnection(){
		// Close connection
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public static void main(String[] args) {
		try {
			// Incorporate mySQL driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Create connection
			Connection conn = DriverManager.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false","root", "bhushan");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println("HelloWorld!!!");

	}
}
