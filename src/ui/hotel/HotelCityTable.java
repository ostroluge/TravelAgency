package ui.hotel; 

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.City;
import model.Hotel;
import ui.MyJTableModel;
import ui.listener.city.CityHotelSelectionListener;
import ui.listener.hotel.HotelCityChangeListener;
import ui.listener.hotel.HotelCitySelectionListener;
import factory.HotelFactory;

@SuppressWarnings("serial")
public class HotelCityTable extends JPanel implements CityHotelSelectionListener, HotelCityChangeListener{

private static List<HotelCitySelectionListener> listeners = new ArrayList<>();
	
	public static HotelCityTable INSTANCE = new HotelCityTable();
	
	protected String[] columnNames = {
		"id", "Nom"
	};
	protected List<Hotel> hotels = new ArrayList<>();
	protected JTable tableHotel;
	protected MyJTableModel tableModel = new MyJTableModel(columnNames, 0);
	protected JScrollPane scrollPane;
	
	protected City citySelected;
	
	public HotelCityTable() {
		CityHotelTable.INSTANCE.addListener(this);
		HotelFactory.getInstance().addListener(this);
		tableHotel = new JTable(tableModel);
		scrollPane = new JScrollPane(tableHotel);
		setTableSelectionMode();
		setPanel();
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
				if (lsm.isSelectionEmpty()) {
					System.out.println("no row selected");
				} else {
					int selectedRow = lsm.getMinSelectionIndex();
					String idHotelSelected = tableHotel.getValueAt(selectedRow, 0).toString();
					Hotel hotel = HotelFactory.getInstance()
							.getHotelById(Integer.parseInt(idHotelSelected));
					if (hotel != null) {
						fireHotelSelection(hotel,tableHotel);
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

	private void getHotelDetails(City city) {
		hotels = HotelFactory.getInstance().getHotelsFromCity(city.getId());
		if (!hotels.isEmpty() && hotels != null) {
			for (Hotel hotel : hotels) {
				Object[] row = {
						hotel.getId(),
						hotel.getName(),
				};
				tableModel.addRow(row);
			}
		} else {
			tableModel.setRowCount(0);
		}
	}
	public void addListener(HotelCitySelectionListener listener) {
		listeners.add(listener);
	}
	
	private static void fireHotelSelection(Hotel hotelSelected , JTable table) {
		for (HotelCitySelectionListener listener : listeners) {
			listener.onHotelSelection(hotelSelected,table);
		}
	}

	@Override
	public void onCitySelection(City city) {
		citySelected = city;
		getHotelDetails(city);
	}

	@Override
	public void hotelCityHasChanged() {
		if(citySelected != null){
			tableModel.setRowCount(0);
			getHotelDetails(citySelected);
		}		
	}
}
