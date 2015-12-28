package ui.customer;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Customer;
import ui.customer.listener.CustomerSelectionListener;
import factory.CustomerFactory;

@SuppressWarnings("serial")
public class CustomerTable extends JPanel {

	private static List<CustomerSelectionListener> listeners = new ArrayList<>();
	
	public static CustomerTable INSTANCE = new CustomerTable();
	
	protected String[] columnNames = {
			"id", "Nom", "Prénom", "Date de naissance", "Ville d'origine"
	};
	protected List<Customer> customers = new ArrayList<>();
	protected JTable table;
	protected MyJTableModel tableModel = new MyJTableModel(columnNames, 0);
	protected JScrollPane scrollPane;
	
	public CustomerTable() {
		getCustomerDetails();
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		setTableSelectionMode();
		setPanel();
	}

	private void getCustomerDetails() {
		customers = CustomerFactory.getInstance().getAllCustomers();
		for (Customer customer : customers) {
			Object[] row = {
					customer.getId(),
					customer.getLastName(),
					customer.getFirstName(),
					customer.getBirthdate(),
					customer.getOriginCity()
			};
			tableModel.addRow(row);
		}
	}

	private void setTableSelectionMode() {
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				if (lsm.isSelectionEmpty()) {
					System.out.println("no row selected");
				} else {
					int selectedRow = lsm.getMinSelectionIndex();
					String idCustomerSelected = table.getValueAt(selectedRow, 0).toString();
					Customer customer = CustomerFactory.getInstance()
							.getCustomerById(Integer.parseInt(idCustomerSelected));
					if (customer != null) {
						fireCustomerSelection(customer);
					}
				}
			}
		});
	}
	
	private void setPanel() {
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.PAGE_START);
		add(table, BorderLayout.CENTER);
	}

	public void addListener(CustomerSelectionListener listener) {
		listeners.add(listener);
	}

	private static void fireCustomerSelection(Customer customerSelected) {
		for (CustomerSelectionListener listener : listeners) {
			listener.onCustomerSelection(customerSelected);
		}
	}
}