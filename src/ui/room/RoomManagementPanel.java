package ui.room;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import job.room.AddRoom;
import job.room.DeleteRoom;
import model.Category;
import model.Room;
import ui.category.CategoryTable;
import ui.listener.category.CategorySelectionListener;
import ui.listener.room.RoomSelectionListener;
import factory.CategoryFactory;

@SuppressWarnings("serial")
public class RoomManagementPanel extends JPanel implements ActionListener,
	RoomSelectionListener, CategorySelectionListener {

	protected JLabel labelNumber = new JLabel();
	protected JLabel labelName = new JLabel();
	
	protected JTextField number;
	protected JTextField name;
	
	protected JButton addButton;
    protected JButton deleteButton;
    protected JButton clearButton;
	
	private Room roomSelected;
	private Category categorySelected;
	private JTable tableSelected;
	
	public RoomManagementPanel() {
		CategoryTable.INSTANCE.addListener(this);
		RoomTable.INSTANCE.addListener(this);
		setLabels();
		setTextFields();
		setButtons();
		setPanel();
	}
	
	private void setLabels() {
		labelNumber.setText("Numéro :");
		labelName.setText("Nom :");
	}
	
	private void setTextFields() {
		Document modelNumber = new PlainDocument();
		Document modelName = new PlainDocument();
		number = new JTextField(modelNumber, "", 10);
		name = new JTextField(modelName, "", 10);
	}
	
	private void setButtons() {
		addButton = new JButton("Ajouter");
        clearButton = new JButton("Clear");
        deleteButton = new JButton("Supprimer");

        addButton.addActionListener(this);
        clearButton.addActionListener(this);
        deleteButton.addActionListener(this);
	}
	
	private void setPanel() {
		setLayout(new GridLayout(2, 4));
		add(labelNumber);
		add(number);
		add(labelName);
		add(name);
		add(addButton);
		add(clearButton);
		add(deleteButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			if (!number.getText().trim().equals("") &&
					!name.getText().trim().equals("")) {
				Room roomToAdd = new Room(Integer.parseInt(number.getText()),
						name.getText());
				if (categorySelected != null) {
					new AddRoom(categorySelected.getHotelId(), categorySelected.getId(),
							roomToAdd.getRoomNumber(), roomToAdd.getNameRoom());
				}
			}
		} else if (e.getSource() == deleteButton) {
			if (roomSelected != null) {
				new DeleteRoom(roomSelected.getIdHotel(), roomSelected.getIdCategory(), roomSelected.getRoomNumber());
			}
		} else if (e.getSource() == clearButton) {
			if (roomSelected != null) {
				clearSelection();
			}
		}
	}

	private void clearSelection() {
		roomSelected = null;
		tableSelected.clearSelection();
		number.setText("");
		name.setText("");
	}
	
	@Override
	public void onRoomSelection(Room room, JTable table) {
		if (room != null) {
			roomSelected = room;
			number.setText(room.getRoomNumber()+"");
			name.setText(room.getNameRoom());
		}
		tableSelected = table;
	}

	@Override
	public void onCategorySelection(Category category, JTable table) {
		if (category != null) {
			categorySelected = CategoryFactory.getInstance().getCategoryById(category.getId());
		}
	}
}
