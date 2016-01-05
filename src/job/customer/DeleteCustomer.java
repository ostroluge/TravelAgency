package job.customer;

import factory.CustomerFactory;

public class DeleteCustomer {

	public DeleteCustomer(Long id) {
		CustomerFactory.getInstance().removeCustomer(id);
	}
}
