package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

	public static DbManager INSTANCE;
	
	public static DbManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DbManager();
		}
		return INSTANCE;
	}

	public Connection getConnection() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:hsqldb: "
							+ "travelAgencyDb",
							"sa",
							"");
			
			if (conn != null) {
				return conn;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
