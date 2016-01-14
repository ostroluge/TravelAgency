package ui.booking;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Booking;

@SuppressWarnings("serial")
public class BookingFlightReturnFrame extends JFrame{

		JPanel container = new JPanel();
		protected Booking booking;
		BookingFlightTable table;
		BookingFlightManagementPanel managementPanel;
		
		public BookingFlightReturnFrame(Booking booking) {
			this.setName("ReturnFrame");
			this.booking = booking;
			table = new BookingFlightTable(booking.getIdCityArrival());
			managementPanel = new BookingFlightManagementPanel(booking);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(640, 400);
			setResizable(false);
			setTitle("Liste des vols - Retour");
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

