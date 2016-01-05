package job.customer;

import factory.CustomerFactory;
import model.Customer;

public class AddCustomer {

	public AddCustomer(Customer customer) {
		CustomerFactory.getInstance().addCustomer(customer.getLastName(),
				customer.getFirstName(), customer.getBirthdate(), customer.getOriginCity());
	}
}
