package MainPacket;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnect {
	private String connectionString = "jdbc:sqlserver://VUTHANHDUONG\\D_DB:1433;database=QLKH_Java;"
			+ "integratedSecurity=false;"
			+ "encrypt=true;"
			+ "trustServerCertificate=true;";
	private Connection connection;
	public SQLConnect() {
		try {			
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
