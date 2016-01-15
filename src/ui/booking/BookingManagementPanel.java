package ui.booking;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import job.booking.BookingManager;
import job.city.CityManager;
import job.customer.CustomerManager;
import model.Booking;
import model.City;
import model.Customer;
import ui.booking.flight.BookingFlightDepartureFrame;
import ui.listener.booking.BookingSelectionListener;
import factory.CityFactory;
import factory.CustomerFactory;

@SuppressWarnings("serial")
public class BookingManagementPanel extends JPanel implements ActionListener, BookingSelectionListener {

	JLabel labelDepartureDate = new JLabel();
	JLabel labelReturnDate = new JLabel();
	JLabel labelDepartureCity = new JLabel();
	JLabel labelArrivalCity = new JLabel();
	JLabel labelNbPassenger = new JLabel();
	
	JTextField departureDate;
	JTextField returnDate;
	JTextField nbPassenger;
	
	JComboBox<Object> departureCity;
	JComboBox<Object> arrivalCity;
	
	JButton bookButton;
	JButton clearButton;
	JButton returnButton;
	JButton deleteButton;
	
	protected String nameCustomer;
	
	protected City dCity;
	protected City aCity;
	
	protected Booking bookingSelected;
	protected JTable tableSelected;
	
	public BookingManagementPanel(String name) {
		this.nameCustomer = name;
		BookingTable.INSTANCE.addListener(this);
		setLabels();
		setTextFields();
		setComboBoxes();
		setButtons();
		setPanel();
	}
	
	private void setLabels() {
		labelDepartureDate.setText("Date de départ :");
		labelReturnDate.setText("Date de retour :");
		labelDepartureCity.setText("Ville de départ :");
		labelArrivalCity.setText("Ville d'arrivée :");
		labelNbPassenger.setText("Nombre de passagers");
	}
	
	private void setTextFields() {
		Document modelDepartureDate = new PlainDocument();
		Document modelReturnDate = new PlainDocument();
		Document modelNbPassenger = new PlainDocument();
		departureDate = new JTextField(modelDepartureDate, "", 10);
		returnDate = new JTextField(modelReturnDate, "", 10);
		nbPassenger = new JTextField(modelNbPassenger, "", 10);
	}
	
	private void setComboBoxes() {
		List<City> cities = CityManager.INSTANCE.getAllCity();
		List<String> nameCities = new ArrayList<>();
		nameCities.add("");
		if (cities != null) {
			for (City city : cities) {
				nameCities.add(city.getNameCity());
			}
			departureCity = new JComboBox<>((nameCities.toArray()));
			arrivalCity = new JComboBox<>(nameCities.toArray());
			departureCity.addActionListener(this);
			arrivalCity.addActionListener(this);
		}
	}
	
	private void setButtons() {
		bookButton = new JButton("Réserver");
		clearButton = new JButton("Clear");
		returnButton = new JButton("Retour");
		deleteButton = new JButton("Supprimer");
		bookButton.addActionListener(this);
		clearButton.addActionListener(this);
		returnButton.addActionListener(this);
		deleteButton.addActionListener(this);
	}
	
	private void setPanel() {
		setLayout(new GridLayout(4, 4));
		add(labelDepartureDate);
		add(departureDate);
		add(labelReturnDate);
		add(returnDate);
		add(labelDepartureCity);
		add(departureCity);
		add(labelArrivalCity);
		add(arrivalCity);
		add(labelNbPassenger);
		add(nbPassenger);
		add(bookButton);
		add(clearButton);
		add(deleteButton);
		add(returnButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		if (e.getSource() == bookButton) {
			if (!departureDate.getText().trim().equals("") &&
					!returnDate.getText().trim().equals("") &&
					!nbPassenger.getText().trim().equals("")) {
				
				if (!departureCity.getSelectedItem().equals(arrivalCity.getSelectedItem())) {
					if (aCity != null && dCity != null) {
						Customer customer = CustomerManager.INSTANCE.getCustomerByName(nameCustomer);
						Booking booking = new Booking(customer.getId(), dCity.getId(), aCity.getId(),
								Integer.parseInt(nbPassenger.getText().trim()), departureDate.getText().trim(),
								returnDate.getText().trim());
						topFrame.dispose();
						BookingFlightDepartureFrame bookingFlightFrame = new BookingFlightDepartureFrame(booking);
						bookingFlightFrame.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(topFrame, "Veuillez choisir 2 villes différentes");
				}
			}
		} else if (e.getSource() == clearButton) {
			if (bookingSelected != null) {
				clearSelection();
			} else {
				departureDate.setText("");
				returnDate.setText("");
				nbPassenger.setText("");
				departureCity.setSelectedItem("");
				arrivalCity.setSelectedItem("");
			}
		} else if (e.getSource() == returnButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			AuthenticationFrame frame = new AuthenticationFrame();
			frame.setVisible(true);
		} else if(e.getSource() == deleteButton){
			if(bookingSelected != null){
				BookingManager.INSTANCE.cancelBooking(bookingSelected.getId());
				JOptionPane.showMessageDialog(topFrame, "La réservation a été annulée");
			}
		}
		else if (e.getSource() == departureCity) {
			String citySelected = (String) departureCity.getSelectedItem();
			City city = CityManager.INSTANCE.getCityByName(citySelected);
			if (city != null) {
				dCity = city;
			}
		} else if (e.getSource() == arrivalCity) {
			String citySelected = (String) arrivalCity.getSelectedItem();
			City city = CityManager.INSTANCE.getCityByName(citySelected);
			if (city != null) {
				aCity = city;
			}
		}
	}

	private void clearSelection() {
		bookingSelected = null;
		tableSelected.clearSelection();
		departureDate.setText("");
		returnDate.setText("");
		nbPassenger.setText("");
		departureCity.setSelectedItem("");
		arrivalCity.setSelectedItem("");
	}
	
	@Override
	public void onBookingSelection(Booking booking, JTable table) {
		if (booking != null) {
			bookingSelected = booking;
			
			City dCity =  CityManager.INSTANCE
					.getCityById(booking.getIdCityDeparture());
			City aCity =  CityManager.INSTANCE
					.getCityById(booking.getIdCityArrival());
			
			departureDate.setText(booking.getDateDeparture());
			returnDate.setText(booking.getDateReturn());
			nbPassenger.setText(booking.getNombrePassagers()+"");
			
			departureCity.setSelectedItem(dCity.getNameCity());
			arrivalCity.setSelectedItem(aCity.getNameCity());
		}
		tableSelected = table;
	}
}
