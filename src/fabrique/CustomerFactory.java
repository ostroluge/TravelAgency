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
	private PreparedStatement preparedStatement;
	
	public static CustomerFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CustomerFactory();
		}
		return INSTANCE;
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
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
	
	public int addCustomer(String lastName, String firstName, String birthdate, String originCity) {
		try {
			preparedStatement = conn.prepareStatement(
					"insert into customer "
					+ "(last_name, first_name, birthdate, origin_city) values (?, ?, ?, ?)");
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, lastName);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, birthdate);
			preparedStatement.setString(4, originCity);
			
			int resultCode = preparedStatement.executeUpdate();
			
			return resultCode;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int removeCustomer(Long id) {
		try {
			preparedStatement = conn.prepareStatement(
					"delete from customer where id = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, id);
			
			int resultCode = preparedStatement.executeUpdate();
			
			return resultCode;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
