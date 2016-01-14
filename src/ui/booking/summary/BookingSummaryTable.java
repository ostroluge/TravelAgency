package ui.booking.summary;

import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Booking;
import ui.MyJTableModel;
import factory.CityFactory;
import factory.HotelFactory;

@SuppressWarnings("serial")
public class BookingSummaryTable extends JPanel {

	private Booking booking;
	
	protected String[] columnNames = {
			"Date départ", "Date retour", "Ville départ", "Ville arrivée", "Nbre passagers","Hotel","Prix total du voyage"
		};
	
	protected JTable table;
	protected MyJTableModel tableModel = new MyJTableModel(columnNames, 0);
	protected JScrollPane scrollPane;
	
	public BookingSummaryTable(Booking booking){
		this.booking = booking;
		getBookingDetails();
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		setPanel();
	}
	
	public void getBookingDetails(){
		DecimalFormat df = new DecimalFormat("0.00");
		Object[] row = {
				booking.getDateDeparture(),
				booking.getDateReturn(),
				CityFactory.getInstance().getCityById(booking.getIdCityDeparture()).getNameCity(),
				CityFactory.getInstance().getCityById(booking.getIdCityArrival()).getNameCity(),
				booking.getNombrePassagers(),
				HotelFactory.getInstance().getHotelById(booking.getIdHotel()).getName(),
				df.format(booking.getPrice())
		};
		tableModel.addRow(row);
	}
	
	private void setPanel() {
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.PAGE_START);
		add(table, BorderLayout.CENTER);
	}
	
}
