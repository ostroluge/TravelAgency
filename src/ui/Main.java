package ui;

import java.sql.SQLException;
import java.util.List;

import model.Customer;
import factory.CustomerFactory;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		List<Customer> customers = CustomerFactory.getInstance().getAllCustomers();
		
		for (Customer customer : customers) {
			System.out.println(customer.getOriginCity());
		}
	}
}
