package job.customer;

import factory.CustomerFactory;
import model.Customer;

public class EditCustomer {

	public EditCustomer(Long id, Customer customer) {
		CustomerFactory.getInstance().editCustomer(id, customer);
	}
}
