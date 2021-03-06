package ui.category;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import job.city.CityManager;
import job.hotel.HotelManager;

import factory.CityFactory;
import model.City;
import model.Hotel;
import ui.MyJTableModel;
import ui.listener.hotel.HotelSelectionListener;
import factory.HotelFactory;

@SuppressWarnings("serial")
public class HotelTable extends JPanel {

private static List<HotelSelectionListener> listeners = new ArrayList<>();
	
	private Long idCity = null; 
	public static HotelTable INSTANCE = new HotelTable();
	
	protected String[] columnNames = {
		"id", "Nom", "Ville"
	};
	protected List<Hotel> hotels = new ArrayList<>();
	protected JTable tableHotel;
	protected MyJTableModel tableModel = new MyJTableModel(columnNames, 0);
	protected JScrollPane scrollPane;
	
	public HotelTable() {
		getHotelDetails();
		tableHotel = new JTable(tableModel);
		scrollPane = new JScrollPane(tableHotel);
		setTableSelectionMode();
		setPanel();
	}
	
	public HotelTable(Long idCity) {
		this.idCity = idCity;
		getHotelDetails();
		tableHotel = new JTable(tableModel);
		scrollPane = new JScrollPane(tableHotel);
		setTableSelectionMode();
		setPanel();
	}
	
	private void getHotelDetails() {
		if(this.idCity != null){
			hotels = HotelManager.INSTANCE.getHotelsFromCity(idCity);
		} else{
			hotels = HotelManager.INSTANCE.getAllHotels();
		}
			if (hotels != null) {
				for (Hotel hotel : hotels) {
					City city = CityManager.INSTANCE.getCityById(hotel.getIdCity());
					Object[] row = {
							hotel.getId(),
							hotel.getName(),
							city.getNameCity()
					};
					tableModel.addRow(row);
				}
			} else {
				tableModel.setRowCount(0);
			}
	}
	
	private void setTableSelectionMode() {
		tableHotel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel listSelectionModel = tableHotel.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				if (!lsm.isSelectionEmpty()) {
					int selectedRow = lsm.getMinSelectionIndex();
					String idHotelSelected = tableHotel.getValueAt(selectedRow, 0).toString();
					Hotel hotel = HotelManager.INSTANCE
							.getHotelById(Long.parseLong(idHotelSelected));
					if (hotel != null) {
						fireHotelSelection(hotel);
					}
				}
			}
		});
	}

	private void setPanel() {
		setLayout(new BorderLayout());
		add(tableHotel.getTableHeader(), BorderLayout.PAGE_START);
		add(tableHotel, BorderLayout.CENTER);
	}

	public void addListener(HotelSelectionListener listener) {
		listeners.add(listener);
	}
	
	private static void fireHotelSelection(Hotel hotelSelected) {
		for (HotelSelectionListener listener : listeners) {
			listener.onHotelSelection(hotelSelected);
		}
	}
}
