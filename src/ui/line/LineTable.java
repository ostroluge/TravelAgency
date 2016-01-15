package ui.line;

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

import model.City;
import model.Line;
import ui.MyJTableModel;
import ui.listener.line.LineChangeListener;
import ui.listener.line.LineSelectionListener;
import factory.CityFactory;
import factory.LineFactory;

@SuppressWarnings("serial")
public class LineTable extends JPanel implements LineChangeListener {

	private static List<LineSelectionListener> listeners = new ArrayList<>();
	
	public static LineTable INSTANCE = new LineTable();
	
	protected String[] columnNames = {
		"id", "Ville départ", "Ville arrivée"
	};
	protected List<Line> lines = new ArrayList<>();
	protected JTable table;
	protected MyJTableModel tableModel = new MyJTableModel(columnNames, 0);
	protected JScrollPane scrollPane;
	
	public LineTable() {
		LineFactory.getInstance().addListener(this);
		getLineDetails();
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		setTableSelectionMode();
		setPanel();
	}
	
	private void getLineDetails() {
		lines = LineFactory.getInstance().getAllLines();
		if (lines != null) {
			for (Line line : lines) {
				City departureCity = CityManager.INSTANCE
						.getCityById(line.getIdDepartureCity());
				City arrivalCity = CityManager.INSTANCE
						.getCityById(line.getIdArrivalCity());
				Object[] row = {
						line.getId(),
						departureCity.getNameCity(),
						arrivalCity.getNameCity()
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
					String idLineSelected = table.getValueAt(selectedRow, 0).toString();
					Line line = LineFactory.getInstance()
							.getLineById(Long.parseLong(idLineSelected));
					if (line != null) {
						fireLineSelection(line, table);
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
	
	public void addListener(LineSelectionListener listener) {
		listeners.add(listener);
	}
	
	private static void fireLineSelection(Line lineSelected, JTable table) {
		for (LineSelectionListener listener : listeners) {
			listener.onLineSelection(lineSelected, table);
		}
	}
	
	@Override
	public void lineHasChanged() {
		tableModel.setRowCount(0);
		getLineDetails();
	}
}
