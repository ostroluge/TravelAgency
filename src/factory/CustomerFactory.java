package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import ui.listener.customer.CustomerChangeListener;
import db.DbManager;

public class CustomerFactory {

	private static CustomerFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;
	
	private static List<CustomerChangeListener> listeners = new ArrayList<>();
	
	public static CustomerFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CustomerFactory();
		}
		return INSTANCE;
	}

	public Customer create(Long id, String lastName, String firstName,
			String birthdate, String originCity){
		return new Customer(id, lastName, firstName, birthdate, originCity);
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
	
	public Customer getCustomerById(int id) {
		Customer customer = null;
		try {
			preparedStatement = conn.prepareStatement(
					"select * from customer where id = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setInt(1, id);
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();
			
			while (resultPreparedStatement.next()) {
				Long idCustomer = resultPreparedStatement.getLong(1);
				String lastName = resultPreparedStatement.getString(2);
				String firstName = resultPreparedStatement.getString(3);
				String birthdate = resultPreparedStatement.getString(4);
				String originCity = resultPreparedStatement.getString(5);
				customer = new Customer(idCustomer, lastName, firstName, birthdate, originCity);
			}
			
			if (customer != null) {
				return customer;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
			
			fireModelChangeEvent();
			
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
			
			fireModelChangeEvent();
			
			return resultCode;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int editCustomer(Long id, Customer customer) {
		try {
			preparedStatement = conn.prepareStatement(
					"update customer set last_name = ?,"
					+ " first_name = ?, birthdate = ?, origin_city = ? where id = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, customer.getLastName());
			preparedStatement.setString(2, customer.getFirstName());
			preparedStatement.setString(3, customer.getBirthdate());
			preparedStatement.setString(4, customer.getOriginCity());
			preparedStatement.setLong(5, id);
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int editCustomerLastName(Long id, String lastName) {
		try {
			preparedStatement = conn.prepareStatement(
					"update customer set last_name = ? where id = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, lastName);
			preparedStatement.setLong(2, id);
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int editCustomerOriginCity(Long id, String originCity) {
		try {
			preparedStatement = conn.prepareStatement(
					"update customer set origin_city = ? where id = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, originCity);
			preparedStatement.setLong(2, id);
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void addListener(CustomerChangeListener listener) {
		listeners.add(listener);
	}

	private static void fireModelChangeEvent() {
		for (CustomerChangeListener listener : listeners) {
			listener.customerHasChanged();
		}
	}
}
