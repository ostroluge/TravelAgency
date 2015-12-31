package ui.city;

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
import ui.city.listener.CityChangeListener;
import ui.city.listener.CitySelectionListener;
import factory.CityFactory;

@SuppressWarnings("serial")
public class CityTable extends JPanel implements CityChangeListener {

	private static List<CitySelectionListener> listeners = new ArrayList<>();
	
	public static CityTable INSTANCE = new CityTable();
	
	protected String[] columnNames = {
		"id", "Nom"	
	};
	protected List<City> cities = new ArrayList<>();
	protected JTable table;
	protected MyJTableModel tableModel = new MyJTableModel(columnNames, 0);
	protected JScrollPane scrollPane;
	
	public CityTable() {
		CityFactory.getInstance().addListener(this);
		getCityDetails();
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		setTableSelectionMode();
		setPanel();
	}
	
	private void getCityDetails() {
		cities = CityFactory.getInstance().getAllCity();
		for (City city : cities) {
			Object[] row = {
					city.getId(),
					city.getNameCity()
			};
			tableModel.addRow(row);
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
				if (lsm.isSelectionEmpty()) {
					System.out.println("no row selected");
				} else {
					int selectedRow = lsm.getMinSelectionIndex();
					String idCustomerSelected = table.getValueAt(selectedRow, 0).toString();
					City city = CityFactory.getInstance()
							.getCityById(Long.parseLong(idCustomerSelected));
					if (city != null) {
						fireCitySelection(city, table);
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
	
	public void addListener(CitySelectionListener listener) {
		listeners.add(listener);
	}
	
	private static void fireCitySelection(City citySelected, JTable table) {
		for (CitySelectionListener listener : listeners) {
			listener.onCitySelection(citySelected, table);
		}
	}
	
	@Override
	public void customerHasChanged() {
		tableModel.setRowCount(0);
		getCityDetails();
	}
}
