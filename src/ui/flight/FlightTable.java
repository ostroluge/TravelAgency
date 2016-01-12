package ui.flight;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Flight;
import model.Line;
import ui.MyJTableModel;
import ui.line.LineTable;
import ui.listener.flight.FlightChangeListener;
import ui.listener.flight.FlightSelectionListener;
import ui.listener.line.LineSelectionListener;
import factory.FlightFactory;

@SuppressWarnings("serial")
public class FlightTable extends JPanel implements LineSelectionListener, FlightChangeListener {

	private static List<FlightSelectionListener> listeners = new ArrayList<>();
	
	public static FlightTable INSTANCE = new FlightTable();
	
	protected String[] columnNames = {
		"id", "Jour de la semaine", "Heure de départ", "Durée du vol (en h)",
		"Places 1ère classe", "Prix 1ère classe", "Places 2nde classe",
		"Prix 2nde classe", "Temps annulation"
	};
	protected List<Flight> flights = new ArrayList<>();
	protected JTable tableFlight;
	protected MyJTableModel tableModel = new MyJTableModel(columnNames, 0);
	protected JScrollPane scrollPane;
	
	protected Line lineSelected;
	
	public FlightTable() {
		LineTable.INSTANCE.addListener(this);
		FlightFactory.getInstance().addListener(this);
		tableFlight = new JTable(tableModel);
		scrollPane = new JScrollPane(tableFlight);
		setTableSelectionMode();
		setPanel();
	}
	
	private void setTableSelectionMode() {
		tableFlight.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel listSelectionModel = tableFlight.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				if (!lsm.isSelectionEmpty()) {
					int selectedRow = lsm.getMinSelectionIndex();
					String idFlight = tableFlight.getValueAt(selectedRow, 0).toString();
					Flight flight = FlightFactory.getInstance()
							.getFlightById(Long.parseLong(idFlight));
					if (flight != null) {
						fireFlightSelection(flight, tableFlight);
					}
				}
			}
		});
	}
	
	private void setPanel() {
		setLayout(new BorderLayout());
		add(tableFlight.getTableHeader(), BorderLayout.PAGE_START);
		add(tableFlight, BorderLayout.CENTER);
	}
	
	private void getFlightDetail(Line line) {
		flights = FlightFactory.getInstance().getFlightsByLineId(line.getId());
		tableModel.setRowCount(0);
		if (flights != null) {
			for (Flight flight : flights) {
				Object[] row = {
					flight.getId(),
					flight.getDayOfWeek(),
					flight.getDepartureTime(),
					flight.getFlightDuration(),
					flight.getMaxPassengerFirstClass(),
					flight.getPriceFirstClass(),
					flight.getMaxPassengerSecondClass(),
					flight.getPriceSecondClass(),
					flight.getCancellationTime()
				};
				tableModel.addRow(row);
			}
		}
	}
	
	public void addListener(FlightSelectionListener listener) {
		listeners.add(listener);
	}
	
	private static void fireFlightSelection(Flight flightSelected, JTable table) {
		for (FlightSelectionListener listener : listeners) {
			listener.onFlightSelection(flightSelected, table);
		}
	}
	
	@Override
	public void flightHasChanged() {
		if (lineSelected != null) {
			tableModel.setRowCount(0);
			getFlightDetail(lineSelected);
		}
	}

	@Override
	public void onLineSelection(Line line, JTable table) {
		lineSelected = line;
		getFlightDetail(line);
	}
}
