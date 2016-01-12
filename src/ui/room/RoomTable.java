package ui.room;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import factory.RoomFactory;
import model.Category;
import model.Hotel;
import model.Room;
import ui.MyJTableModel;
import ui.category.CategoryTable;
import ui.category.HotelTable;
import ui.listener.category.CategorySelectionListener;
import ui.listener.hotel.HotelSelectionListener;
import ui.listener.room.RoomChangeListener;
import ui.listener.room.RoomSelectionListener;

@SuppressWarnings("serial")
public class RoomTable extends JPanel implements RoomChangeListener, CategorySelectionListener, HotelSelectionListener {

	private static List<RoomSelectionListener> listeners = new ArrayList<>();
	
	public static RoomTable INSTANCE = new RoomTable();
	
	protected String[] columnNames = {
		"Numéro", "Nom"	
	};
	protected List<Room> rooms = new ArrayList<>();
	protected JTable tableRoom;
	protected MyJTableModel tableModel = new MyJTableModel(columnNames, 0);
	protected JScrollPane scrollPane;
	
	private Category categorySelected;
	
	public RoomTable() {
		HotelTable.INSTANCE.addListener(this);
		CategoryTable.INSTANCE.addListener(this);
		RoomFactory.getInstance().addListener(this);
		tableRoom = new JTable(tableModel);
		scrollPane = new JScrollPane(tableRoom);
		setTableSelectionMode();
		setPanel();
	}
	
	private void setTableSelectionMode() {
		tableRoom.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel listSelectionModel = tableRoom.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				if (!lsm.isSelectionEmpty()) {
					int selectedRow = lsm.getMinSelectionIndex();
					String roomNumberSelected = tableRoom.getValueAt(selectedRow, 0).toString();
					
					if (categorySelected != null) {
						Room room = RoomFactory.getInstance()
								.getRoomByIds(categorySelected.getId(), Integer.parseInt(roomNumberSelected));
						if (room != null) {
							fireRoomSelection(room, tableRoom);
						}
					}
				}
			}
		});
	}
	
	private void setPanel() {
		setLayout(new BorderLayout());
		add(tableRoom.getTableHeader(), BorderLayout.PAGE_START);
		add(tableRoom, BorderLayout.CENTER);
	}
	
	private void getRoomDetail(Category category) {
		rooms = RoomFactory.getInstance().getRoomsByCategory(category.getId());
		
		if (rooms != null) {
			for (Room room : rooms) {
				Object[] row = {
						room.getRoomNumber(),
						room.getNameRoom()
				};
				tableModel.addRow(row);
			}
		} else {
			tableModel.setRowCount(0);
		}
	}
	
	public void addListener(RoomSelectionListener listener) {
		listeners.add(listener);
	}
	
	private static void fireRoomSelection(Room roomSelected, JTable table) {
		for (RoomSelectionListener listener : listeners) {
			listener.onRoomSelection(roomSelected, table);
		}
	}
	
	@Override
	public void onCategorySelection(Category category, JTable table) {
		categorySelected = category;
		getRoomDetail(category);
	}

	//A la selection d'un hotel on vide la table model et on réinitialise la catégorie
	public void onHotelSelection(Hotel hotel) {
		categorySelected = null;
		tableModel.setRowCount(0);
	}
	
	@Override
	public void roomHasChanged() {
		if (categorySelected != null) {
			tableModel.setRowCount(0);
			getRoomDetail(categorySelected);
		}
	}

}
