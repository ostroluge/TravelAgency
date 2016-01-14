package job.customer;

import model.Customer;
import factory.CustomerFactory;

/**
 * Classe permettant de gerer les clients
 */
public class CustomerManager {

	public static CustomerManager INSTANCE = new CustomerManager();

	/**
	 * Ajoute un client en base de donnees
	 * @param customer
	 */
	public void addCustomer(Customer customer){
		CustomerFactory.getInstance().addCustomer(customer.getLastName(),
				customer.getFirstName(), customer.getBirthdate(), customer.getOriginCity());
	}
	
	/**
	 * Supprimer un client en base de donnees
	 * @param id
	 */
	public void deleteCustomer(Long id){
		CustomerFactory.getInstance().removeCustomer(id);
	}
	
	/**
	 * Modifie un client en base de donnees
	 * @param id
	 * @param customer
	 */
	public void editCustomer(Long id, Customer customer){
		CustomerFactory.getInstance().editCustomer(id, customer);
	}
}
