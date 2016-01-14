package ui.booking.flight;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Flight;
import ui.MyJTableModel;
import ui.listener.booking.BookingFlightSelectionListener;
import factory.FlightFactory;

@SuppressWarnings("serial")
public class BookingFlightTable extends JPanel {

	private static List<BookingFlightSelectionListener> listeners = new ArrayList<>();

	Long idCityDeparture;
	
	protected String[] columnNames = {
			"id", "Temps d'annulation", "Jour de la semaine", "Temps avant départ", "Durée du vol", "Price première classe", "Prix deuxième classe"
	};
	
	protected JTable table;
	protected MyJTableModel tableModel = new MyJTableModel(columnNames, 0);
	protected JScrollPane scrollPane;

	public static BookingFlightTable INSTANCE = new BookingFlightTable();
	
	public BookingFlightTable(Long idCity){
		idCityDeparture = idCity;
		getFlightDetails();
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		setTableSelectionMode();
		setPanel();
	}
	
	public BookingFlightTable(){
		
	}
	
	public void getFlightDetails(){
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		if(idCityDeparture != null){
			List<Flight> listFlight = FlightFactory.getInstance().getFlightByIdCityDeparture(idCityDeparture);
			for(Flight flight : listFlight){
				Object[] row = {
						flight.getId(),
						flight.getCancellationTime(),
						flight.getDayOfWeek(),
						flight.getDepartureTime(),
						flight.getFlightDuration(),
						flight.getPriceFirstClass(),
						flight.getPriceSecondClass()
				};
				tableModel.addRow(row);
			}
		}
		else {
			tableModel.setRowCount(0);
			JOptionPane.showMessageDialog(topFrame, "Aucun vol pour ces destinations");
		}
	}
	
	private void setPanel() {
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.PAGE_START);
		add(table, BorderLayout.CENTER);
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
					String idFlightSelected = table.getValueAt(selectedRow, 0).toString();
					Flight flight= FlightFactory.getInstance().getFlightById(Long.parseLong(idFlightSelected));
					if (flight != null) {
						fireBookingFlightSelection(flight, table);
					}
				}
			}
		});
	}

	public void addListener(BookingFlightSelectionListener listener) {
		listeners.add(listener);
	}
	
	private static void fireBookingFlightSelection(Flight flightSelected, JTable table) {
		for (BookingFlightSelectionListener listener : listeners) {
			listener.onBookingFlightSelection(flightSelected, table);
		}
	}
	
}
