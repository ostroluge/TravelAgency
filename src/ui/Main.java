package ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbManager;

public class Main {

	public static void main(String[] args) throws SQLException {
		Connection conn = DbManager.getInstance().getConnection();
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(
				"select * from test");
		
		ResultSet resultPreparedStatement = preparedStatement.executeQuery();

		while (resultPreparedStatement.next()) {
			String nom = resultPreparedStatement.getString(1);
			System.out.println(nom);
		}
	}
}
