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
import factory.CategoryFactory;
import ui.listener.category.CategoryChangeListener;
import ui.listener.category.CategorySelectionListener;
import ui.listener.hotel.HotelSelectionListener;

@SuppressWarnings("serial")
public class CategoryTable extends JPanel implements HotelSelectionListener, CategoryChangeListener {

	private static List<CategorySelectionListener> listeners = new ArrayList<>();

	public static CategoryTable INSTANCE = new CategoryTable();

	protected String[] columnNames = {
		"id", "Nom", "Prix", "Capacité"
	};
	protected List<Category> categories = new ArrayList<>();
	protected JTable tableCategory;
	protected MyJTableModel tableModel = new MyJTableModel(columnNames, 0);
	protected JScrollPane scrollPane;

	protected Hotel hotelSelected;

	public CategoryTable() {
		HotelTable.INSTANCE.addListener(this);
		CategoryFactory.getInstance().addListener(this);
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
						fireCategorySelection(category, tableCategory);
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
				category.getName(),
				category.getPrice(),
				category.getCapacity()
			};
			tableModel.addRow(row);
		}
	}

	public void addListener(CategorySelectionListener listener) {
		listeners.add(listener);
	}

	private static void fireCategorySelection(Category categorySelected, JTable table) {
		for (CategorySelectionListener listener : listeners) {
			listener.onCategorySelection(categorySelected, table);
		}
	}

	@Override
	public void onHotelSelection(Hotel hotel) {
		hotelSelected = hotel;
		getCategoryDetail(hotel);
	}

	@Override
	public void categoryHasChanged() {
		if (hotelSelected != null) {
			tableModel.setRowCount(0);
			getCategoryDetail(hotelSelected);
		}
	}
}
