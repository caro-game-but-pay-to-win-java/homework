package Mainpacket;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnect {
	private Connection connection;
	public SQLConnect(String databaseName) {
		try {
			String connectionString = "jdbc:sqlserver://VUTHANHDUONG\\D_DB:1433;database="
					+ databaseName + ";"
					+ "integratedSecurity=false;"
					+ "encrypt=true;"
					+ "trustServerCertificate=true;";
			connection = null;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(connectionString, "sa", "123");
			System.out.println("Good!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
}
