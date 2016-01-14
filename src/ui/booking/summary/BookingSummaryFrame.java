package ui.booking.summary;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Booking;

@SuppressWarnings("serial")
public class BookingSummaryFrame extends JFrame{

	JPanel container = new JPanel();
	BookingSummaryTable table;
	BookingSummaryManagementPanel managementPanel;
	
	public BookingSummaryFrame(Booking booking){
		table = new BookingSummaryTable(booking);
		managementPanel = new BookingSummaryManagementPanel(booking);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(850, 400);
		setResizable(false);
		setTitle("Récapitulatif du voyage");
		setLocationRelativeTo(null);
		
		initContainer();
		setContentPane(container);
	}
	
	private void initContainer() {
		container.setLayout(new GridLayout(2, 1));
		container.add(table);
		container.add(managementPanel);
	}
}
