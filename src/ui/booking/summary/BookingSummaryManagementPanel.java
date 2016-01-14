package ui.booking.summary;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import job.booking.BookingManager;
import model.Booking;
import ui.menu.MainMenuFrame;

@SuppressWarnings("serial")
public class BookingSummaryManagementPanel extends JPanel implements ActionListener{

	private Booking booking;
	JButton validationButton;
	JButton cancelButton;
	
	public BookingSummaryManagementPanel(Booking booking){
		this.booking = booking;
		setButtons();
		setPanel();
	}
	
	private void setButtons(){
		validationButton = new JButton("Valider la réservation");
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
			BookingManager.INSTANCE.addBooking(booking);
			JOptionPane.showMessageDialog(topFrame, "Votre voyage est validé !");
			topFrame.dispose();
			MainMenuFrame frame = new MainMenuFrame();
			frame.setVisible(true);
		}
		if(e.getSource() == cancelButton){
			JOptionPane.showMessageDialog(topFrame, "Réservation annulée.");
			topFrame.dispose();
			MainMenuFrame frame = new MainMenuFrame();
			frame.setVisible(true);
		}
	}

}
