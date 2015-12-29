package ui.customer.listener;

import javax.swing.JTable;

import model.Customer;

public interface CustomerSelectionListener {

	public void onCustomerSelection(Customer customer, JTable table);
}
