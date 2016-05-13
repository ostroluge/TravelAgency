package ui.booking.room;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Booking;
import ui.room.RoomTable;
import ui.room.RoomCategoryTableContainer;

@SuppressWarnings("serial")
public class BookingRoomFrame extends JFrame{

	protected Booking booking;
	JPanel container = new JPanel();
	BookingRoomTableContainer tableContainer;
	BookingRoomManagementPanel managementPanel;
	
	public BookingRoomFrame(Booking booking){
		this.booking = booking;
		tableContainer  = new BookingRoomTableContainer(booking.getIdCityArrival());
		managementPanel = new BookingRoomManagementPanel(booking);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 400);
		setResizable(false);
		setTitle("Liste des  hôtels et des chambres");
		setLocationRelativeTo(null);

		initContainer();
		setContentPane(container);
	}
	
	private void initContainer() {
		container.setLayout(new GridLayout(2, 1));
		container.add(tableContainer);
		container.add(managementPanel);
	}

}
