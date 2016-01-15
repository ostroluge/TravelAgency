package ui.room;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import job.category.CategoryManager;
import job.room.RoomManager;
import model.Category;
import model.Room;
import ui.category.CategoryTable;
import ui.listener.category.CategorySelectionListener;
import ui.listener.room.RoomSelectionListener;
import ui.menu.MainMenuFrame;

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
	protected JButton returnButton;
	
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
		returnButton = new JButton("Retour");

        addButton.addActionListener(this);
        clearButton.addActionListener(this);
        deleteButton.addActionListener(this);
		returnButton.addActionListener(this);
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
		add(returnButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		if (e.getSource() == addButton) {
			if (!number.getText().trim().equals("") &&
					!name.getText().trim().equals("")) {
				Room roomToAdd = new Room(Integer.parseInt(number.getText()),
						name.getText());
				if (categorySelected != null) {
					RoomManager.INSTANCE.addRoom(categorySelected.getHotelId(), categorySelected.getId(),
							roomToAdd.getRoomNumber(), roomToAdd.getNameRoom());
				}
				else{
					 JOptionPane.showMessageDialog(topFrame, "Veuillez sélectionner un hôtel et une catégorie");
				}
			}
			else{
				 JOptionPane.showMessageDialog(topFrame, "Veuillez remplir les champs");
			}
		} else if (e.getSource() == deleteButton) {
			if (roomSelected != null) {
				RoomManager.INSTANCE.deleteRoom(roomSelected.getIdHotel(), roomSelected.getIdCategory(), roomSelected.getRoomNumber());
				clearSelection();
			}
			else{
				 JOptionPane.showMessageDialog(topFrame, "Veuillez sélectionner une chambre");
			}
		} else if (e.getSource() == clearButton) {
			if (roomSelected != null) {
				clearSelection();
			} else {
				number.setText("");
				name.setText("");
			}
		} else if (e.getSource() == returnButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			MainMenuFrame frame = new MainMenuFrame();
			frame.setVisible(true);
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
			categorySelected = CategoryManager.INSTANCE.getCategoryById(category.getId());
		}
	}
}
