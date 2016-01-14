package ui.booking.room;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import job.category.CategoryManager;
import job.room.RoomManager;

import factory.CategoryFactory;
import factory.RoomFactory;

import ui.listener.room.RoomSelectionListener;
import ui.menu.MainMenuFrame;
import ui.room.RoomTable;

import model.Booking;
import model.Room;

@SuppressWarnings("serial")
public class BookingRoomManagementPanel extends JPanel implements ActionListener, RoomSelectionListener{

	protected Booking booking;
	protected JButton validationButton;
	protected JButton cancelButton;
	
	private Room roomSelected;
	private JTable tableSelected;

	public BookingRoomManagementPanel(Booking booking){
		this.booking = booking;
		RoomTable.INSTANCE.addListener(this);
		setButtons();
		setPanel();
	}

	private void setButtons(){
		validationButton = new JButton("Valider");
		cancelButton = new JButton("Annuler");
		
		validationButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}

	private void setPanel(){
		setLayout(new GridLayout(1,2));
		add(validationButton);
		add(cancelButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		if(e.getSource() == validationButton){
			if(roomSelected != null){
				booking.setIdHotel(roomSelected.getIdHotel());
				booking.setIdCategory(roomSelected.getIdCategory());
				booking.setRoomNumber(roomSelected.getRoomNumber());
				
				//Calcul du prix
				int passagers = booking.getNombrePassagers();
				float priceBooking = booking.getPrice();
				float priceRoom = CategoryFactory.getInstance().getCategoryById(roomSelected.getIdCategory()).getPrice();
				
				priceBooking += (priceRoom * passagers);
				
				booking.setPrice(priceBooking);
				
				 JOptionPane.showMessageDialog(topFrame, "PRIX TOTAL : "+priceBooking+"€");
			}
		}
		else if(e.getSource() == cancelButton){
			topFrame.dispose();
			MainMenuFrame frame = new MainMenuFrame();
			frame.setVisible(true);
		}
	}
	
	@Override
	public void onRoomSelection(Room room, JTable table) {
		if(room != null){
			roomSelected = room;
		}
		
		tableSelected = table;
	}
	
}
