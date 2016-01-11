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
import ui.MyJTableModel;
import ui.listener.city.CityHotelSelectionListener;
import factory.CityFactory;

@SuppressWarnings("serial")
public class CityHotelTable extends JPanel{

		private static List<CityHotelSelectionListener> listeners = new ArrayList<>();
		
		public static CityHotelTable INSTANCE = new CityHotelTable();
		
		protected String[] columnNames = {
			"id", "Nom"	
		};
		protected List<City> cities = new ArrayList<>();
		protected JTable table;
		protected MyJTableModel tableModel = new MyJTableModel(columnNames, 0);
		protected JScrollPane scrollPane;
		
		public CityHotelTable() {
			getCityDetails();
			table = new JTable(tableModel);
			scrollPane = new JScrollPane(table);
			setTableSelectionMode();
			setPanel();
		}
		
		private void getCityDetails() {
			cities = CityFactory.getInstance().getAllCity();
			if (cities != null) {
				for (City city : cities) {
					Object[] row = {
							city.getId(),
							city.getNameCity()
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
						String idCustomerSelected = table.getValueAt(selectedRow, 0).toString();
						City city = CityFactory.getInstance()
								.getCityById(Long.parseLong(idCustomerSelected));
						if (city != null) {
							fireCitySelection(city);
						}
					}
				}
			});
		}
		
		private void setPanel() {
			setLayout(new BorderLayout());
			add(table.getTableHeader(), BorderLayout.PAGE_START);
			add(table, BorderLayout.CENTER);
		}
		
		public void addListener(CityHotelSelectionListener listener) {
			listeners.add(listener);
		}
		
		private static void fireCitySelection(City citySelected) {
			for (CityHotelSelectionListener listener : listeners) {
				listener.onCitySelection(citySelected);
			}
		}
		
	}

