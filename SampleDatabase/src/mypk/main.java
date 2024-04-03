package mypk;
import java.sql.*;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String conns = "jdbc:sqlserver://localhost:1433;database=DB;"
	            + "integratedSecurity=false;"
				+ "encrypt=true;trustServerCertificate=true";
		try {
			Connection conn = null;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(conns, "sa", "09122002");
			System.out.println("heeh bi" + conn.getCatalog());
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("loz");
		}
	}

}
