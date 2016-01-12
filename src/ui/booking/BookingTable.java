package ui.booking;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Booking;
import ui.MyJTableModel;
import ui.listener.booking.BookingChangeListener;
import ui.listener.booking.BookingSelectionListener;

@SuppressWarnings("serial")
public class BookingTable extends JPanel implements BookingChangeListener {

	private static List<BookingSelectionListener> listeners = new ArrayList<>();
	
	public static BookingTable INSTANCE = new BookingTable();
	
	protected String[] columnNames = {
		"id", "Date départ", "Date retour"	
	};
	protected List<Booking> bookings = new ArrayList<>();
	protected JTable table;
	protected MyJTableModel tableModel = new MyJTableModel(columnNames, 0);
	protected JScrollPane scrollPane;
	
	protected String nameCustomer;
	
	public BookingTable() {
		
	}
	
	public BookingTable(String name) {
		this.nameCustomer = name;
//		BookingFactory.getInstance().addListener(this);
		getBookingDetails();
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		setTableSelectionMode();
		setPanel();
	}
	
	private void getBookingDetails() {
//		bookings = BookingFactory.getInstance().getBookingsByCustomer();
		if (bookings != null) {
			for (Booking booking : bookings) {
				Object[] row = {
//						booking.getId(),
//						booking.getDepartureDate(),
//						booking.getReturnDate()
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
					String idBookingSelected = table.getValueAt(selectedRow, 0).toString();
//					Booking booking = BookingFactory.getInstance()
//							.getBookingById(Long.parseLong(idBookingSelected));
//					if (booking != null) {
//						fireBookingSelection(booking, table);
//					}
				}
			}
		});
	}
	
	private void setPanel() {
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.PAGE_START);
		add(table, BorderLayout.CENTER);
	}
	
	public void addListener(BookingSelectionListener listener) {
		listeners.add(listener);
	}
	
	private static void fireBookingSelection(Booking bookingSelected, JTable table) {
		for (BookingSelectionListener listener : listeners) {
			listener.onBookingSelection(bookingSelected, table);
		}
	}
	
	@Override
	public void bookingHasChanged() {
		tableModel.setRowCount(0);
		getBookingDetails();
	}
}
