package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

	protected String url = "jdbc:mysql://mysql-thomasostrowski.alwaysdata.net:3306/thomasostrowski_travelagency";
	protected String user = "106041";
	protected String password = "miage";
	
	public static DbManager INSTANCE;
		
	public static DbManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DbManager();
		}
		return INSTANCE;
	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				return conn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
