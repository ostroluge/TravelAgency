package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("./src/db/resources/database.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Connection conn = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
			
			if (conn != null) {
				return conn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
