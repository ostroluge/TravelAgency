package ui.booking;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BookingFrame extends JFrame {

	JPanel container = new JPanel();
	BookingTable table;
	BookingManagementPanel managementPanel = new BookingManagementPanel();

	private String nameCustomer;
	
	public BookingFrame(String name) {
		this.nameCustomer = name;
		table = new BookingTable(nameCustomer);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 400);
		setResizable(false);
		setTitle("Liste des réservations");
		setLocationRelativeTo(null);
		
		initContainer();
		setContentPane(container);
	}

	private void initContainer() {
		container.setLayout(new GridLayout(2, 1));
		container.add(table);
		container.add(managementPanel);
	}

	public void setNameCustomer(String name) {
		this.nameCustomer = name;
	}
}
