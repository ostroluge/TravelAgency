package ui.booking;

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

import model.Booking;
import ui.listener.booking.BookingSelectionListener;

@SuppressWarnings("serial")
public class BookingManagementPanel extends JPanel implements ActionListener, BookingSelectionListener {

	JLabel labelDepartureDate = new JLabel();
	JLabel labelReturnDate = new JLabel();
	
	JTextField departureDate;
	JTextField returnDate;
	
	JButton bookButton;
	JButton cancelButton;
	JButton clearButton;
	JButton returnButton;
	
	protected Booking bookingSelected;
	protected JTable tableSelected;
	
	public BookingManagementPanel() {
		BookingTable.INSTANCE.addListener(this);
		setLabels();
		setTextFields();
		setButtons();
		setPanel();
	}
	
	private void setLabels() {
		labelDepartureDate.setText("Date de départ :");
		labelReturnDate.setText("Date de retour :");
	}
	
	private void setTextFields() {
		Document modelDepartureDate = new PlainDocument();
		Document modelReturnDate = new PlainDocument();
		departureDate = new JTextField(modelDepartureDate, "", 10);
		returnDate = new JTextField(modelReturnDate, "", 10);
	}
	
	private void setButtons() {
		bookButton = new JButton("Réserver");
		cancelButton = new JButton("Annuler");
		clearButton = new JButton("Clear");
		returnButton = new JButton("Retour");
		
		bookButton.addActionListener(this);
		cancelButton.addActionListener(this);
		clearButton.addActionListener(this);
		returnButton.addActionListener(this);
	}
	
	private void setPanel() {
		setLayout(new GridLayout(2, 4));
		add(labelDepartureDate);
		add(departureDate);
		add(labelReturnDate);
		add(returnDate);
		add(bookButton);
		add(cancelButton);
		add(clearButton);
		add(returnButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bookButton) {
			System.out.println("BOOK");
		} else if (e.getSource() == cancelButton) {
			System.out.println("CANCEL");
		} else if (e.getSource() == clearButton) {
			System.out.println("CLEAR");
		} else if (e.getSource() == returnButton) {
			System.out.println("RETOUR");
		}
	}

	private void clearSelection() {
		bookingSelected = null;
		tableSelected.clearSelection();
		departureDate.setText("");
		returnDate.setText("");
	}
	
	@Override
	public void onBookingSelection(Booking booking, JTable table) {
		if (booking != null) {
			bookingSelected = booking;
//			departureDate.setText(booking.getDepartureDate());
//			returnDate.setText(booking.getReturnDate());
		}
		tableSelected = table;
	}
}
