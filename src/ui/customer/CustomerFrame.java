package ui.customer;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CustomerFrame extends JFrame {

	JPanel container = new JPanel();
	CustomerTable table = new CustomerTable();
	ManagementPanel managementPanel = new ManagementPanel();
	
	public CustomerFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 400);
		setResizable(false);
		setTitle("Liste des clients");
		setLocationRelativeTo(null);
		
		initContainer();
		setContentPane(container);
	}
	
	private void initContainer() {
		container.setLayout(new GridLayout(2,1));
		container.add(table);
		container.add(managementPanel);
	}
}
