package ui.booking.room;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import job.category.CategoryManager;
import model.Booking;
import model.Room;
import ui.booking.summary.BookingSummaryFrame;
import ui.listener.room.RoomSelectionListener;
import ui.menu.MainMenuFrame;
import ui.room.RoomTable;

@SuppressWarnings("serial")
public class BookingRoomManagementPanel extends JPanel implements ActionListener, RoomSelectionListener{

	protected Booking booking;
	protected JButton validationButton;
	protected JButton cancelButton;
	
	private Room roomSelected;

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
				float priceRoom = CategoryManager.INSTANCE.getCategoryById(roomSelected.getIdCategory()).getPrice();
				Long days = this.getNumberDays(booking.getDateDeparture(), booking.getDateReturn());
				priceBooking += (priceRoom * passagers * days);
				
				booking.setPrice(priceBooking);

				topFrame.dispose();
				BookingSummaryFrame frame = new BookingSummaryFrame(booking);
				frame.setVisible(true);
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
	}

	public long getNumberDays(String departureDate, String returnDate) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
        try {
            Date dateDepart = format.parse(departureDate);
            Date dateReturn = format.parse(returnDate);

            long diffMillies = dateReturn.getTime() - dateDepart.getTime();    
            
            return TimeUnit.DAYS.convert(diffMillies, TimeUnit.MILLISECONDS);
            
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }
}
