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

import job.customer.CustomerManager;

import model.Customer;
import ui.MyJTableModel;
import ui.listener.customer.CustomerChangeListener;
import ui.listener.customer.CustomerSelectionListener;
import factory.CustomerFactory;

@SuppressWarnings("serial")
public class CustomerTable extends JPanel implements CustomerChangeListener {

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
		CustomerFactory.getInstance().addListener(this);
		getCustomerDetails();
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		setTableSelectionMode();
		setPanel();
	}

	private void getCustomerDetails() {
		customers = CustomerManager.INSTANCE.getAllCustomers();
		if (customers != null) {
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
		} else {
			tableModel.setRowCount(0);
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
				if (!lsm.isSelectionEmpty()) {
					int selectedRow = lsm.getMinSelectionIndex();
					String idCustomerSelected = table.getValueAt(selectedRow, 0).toString();
					Customer customer = CustomerManager.INSTANCE
							.getCustomerById(Integer.parseInt(idCustomerSelected));
					if (customer != null) {
						fireCustomerSelection(customer, table);
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

	private static void fireCustomerSelection(Customer customerSelected, JTable table) {
		for (CustomerSelectionListener listener : listeners) {
			listener.onCustomerSelection(customerSelected, table);
		}
	}

	@Override
	public void customerHasChanged() {
		tableModel.setRowCount(0);
		getCustomerDetails();
	}
}
