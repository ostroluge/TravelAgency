package ui.booking.flight;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Booking;

@SuppressWarnings("serial")
public class BookingFlightDepartureFrame extends JFrame {
	JPanel container = new JPanel();
	protected Booking booking;
	BookingFlightTable table;
	BookingFlightManagementPanel managementPanel;
	
	public BookingFlightDepartureFrame(Booking booking) {
		this.setName("DepartureFrame");
		this.booking = booking;
		table = new BookingFlightTable(booking.getIdCityDeparture());
		managementPanel = new BookingFlightManagementPanel(booking);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 400);
		setResizable(false);
		setTitle("Liste des vols - Aller");
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
