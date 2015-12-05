package fabrique;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbManager;
import domaine.Customer;

public class CustomerFactory {

	private static CustomerFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	
	public static CustomerFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CustomerFactory();
		}
		return INSTANCE;
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(
					"select * from customer");
			preparedStatement.clearParameters();
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();

			while (resultPreparedStatement.next()) {
				Long id = resultPreparedStatement.getLong(1);
				String lastName = resultPreparedStatement.getString(2);
				String firstName = resultPreparedStatement.getString(3);
				String birthdate = resultPreparedStatement.getString(4);
				String originCity = resultPreparedStatement.getString(5);
				customers.add(new Customer(id, lastName, firstName, birthdate, originCity));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		if (customers != null) {
			return customers;
		}
		return null;
	}
}
