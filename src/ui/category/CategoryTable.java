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

import model.Category;
import model.Hotel;
import ui.MyJTableModel;
import ui.category.listener.HotelSelectionListener;
import factory.CategoryFactory;

@SuppressWarnings("serial")
public class CategoryTable extends JPanel implements HotelSelectionListener {

	protected String[] columnNames = {
		"id", "Capacite", "Prix", "Nom"	
	};
	protected List<Category> categories = new ArrayList<>();
	protected JTable tableCategory;
	protected MyJTableModel tableModel = new MyJTableModel(columnNames, 0);
	protected JScrollPane scrollPane;

	public CategoryTable() {
		HotelTable.INSTANCE.addListener(this);
		tableCategory = new JTable(tableModel);
		scrollPane = new JScrollPane(tableCategory);
		setTableSelectionMode();
		setPanel();
	}

	private void setTableSelectionMode() {
		tableCategory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel listSelectionModel = tableCategory.getSelectionModel();
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
					String idCategorySelected = tableCategory.getValueAt(selectedRow, 0).toString();
					Category category = CategoryFactory.getInstance()
							.getCategoryById(Integer.parseInt(idCategorySelected));
					if (category != null) {
//						fireCustomerSelection(hotel, tableHotel);
					}
				}
			}
		});
	}
	
	private void setPanel() {
		setLayout(new BorderLayout());
		add(tableCategory.getTableHeader(), BorderLayout.PAGE_START);
		add(tableCategory, BorderLayout.CENTER);
	}

	private void getCategoryDetail(Hotel hotel) {
		categories = CategoryFactory.getInstance().getCategoriesByHotelId(hotel.getId());
		for (Category category : categories) {
			Object[] row = {
				category.getId(),
				category.getCapacity(),
				category.getPrice(),
				category.getName()
			};
			tableModel.addRow(row);
		}
	}
	
	@Override
	public void onHotelSelection(Hotel hotel, JTable table) {
		getCategoryDetail(hotel);
	}
}
