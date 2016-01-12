package job.customer;

import model.Customer;
import factory.CustomerFactory;


public class CustomerManager {

	public static CustomerManager INSTANCE = new CustomerManager();

	public void addCustomer(Customer customer){
		CustomerFactory.getInstance().addCustomer(customer.getLastName(),
				customer.getFirstName(), customer.getBirthdate(), customer.getOriginCity());
	}
	
	public void deleteCustomer(Long id){
		CustomerFactory.getInstance().removeCustomer(id);
	}
	
	public void editCustomer(Long id, Customer customer){
		CustomerFactory.getInstance().editCustomer(id, customer);
	}
}
