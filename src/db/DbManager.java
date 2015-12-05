package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

	public Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("TravelAgency/src/db/resources/database.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Connection conn = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
		
		if (conn != null) {
			return conn;
		}

		return null;
	}
}
