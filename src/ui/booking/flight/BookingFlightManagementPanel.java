package ui.booking.flight;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import model.Booking;
import model.Flight;
import ui.booking.room.BookingRoomFrame;
import ui.listener.booking.BookingFlightSelectionListener;
import ui.menu.MainMenuFrame;

@SuppressWarnings("serial")
public class BookingFlightManagementPanel extends JPanel implements ActionListener, BookingFlightSelectionListener{

	JLabel labelChoixClasse = new JLabel();
	JComboBox<Object> choixClasse;
	protected Booking booking; 
	Boolean departureFlight;
	Boolean returnFlight;
	protected String classe;
	protected Long idFlight;
	protected Float priceFirstClass;
	protected Float priceSecondClass;
	JButton validationButton;
	JButton cancelButton;
	
	protected JTable tableSelected;

	public BookingFlightManagementPanel(Booking booking){
		this.booking = booking;
		BookingFlightTable.INSTANCE.addListener(this);
		setLabels();
		setComboBox();
		setButtons();
		setPanel();
	}
	
	private void setLabels(){
		labelChoixClasse.setText("Choix de la classe :");
	}
	
	private void setComboBox(){
		List<String> choixClasse = new ArrayList<>();
		choixClasse.add("");
		choixClasse.add("1");
		choixClasse.add("2");
		
		this.choixClasse = new JComboBox<>(choixClasse.toArray());
		this.choixClasse.addActionListener(this);
	}

	private void setButtons(){
		validationButton = new JButton("Valider");
		cancelButton = new JButton("Annuler");
		
		validationButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}
	
	private void setPanel(){
		setLayout(new GridLayout(2,2));
		add(labelChoixClasse);
		add(choixClasse);
		add(validationButton);
		add(cancelButton);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		DecimalFormat df = new DecimalFormat("0.00");
		if(e.getSource() == validationButton){
			if(classe != null && !classe.equals("") && idFlight != null && priceFirstClass != null && priceSecondClass != null){
				int nombrePassagers = booking.getNombrePassagers();
				if(classe.equals("1")){
					if(Double.isNaN(booking.getPrice())){
						booking.setPrice(priceFirstClass * nombrePassagers);
						JOptionPane.showMessageDialog(topFrame, "Le prix des billets est de : " + df.format(booking.getPrice())+"€");
					}
					else{
						float price = booking.getPrice();
						float additionnalPrice = (priceFirstClass * nombrePassagers);
						price += additionnalPrice;
						booking.setPrice(price);
						JOptionPane.showMessageDialog(topFrame, "Le prix des billets est de : " + df.format(additionnalPrice) +"€\nLe prix total est de : " + df.format(booking.getPrice())+"€");
					}
				}
				else if(classe.equals("2")){
					if(Double.isNaN(booking.getPrice())){
						booking.setPrice(priceSecondClass * nombrePassagers);
						JOptionPane.showMessageDialog(topFrame, "Le prix des billets est de : " + df.format(booking.getPrice())+"€");
					}
					else{
						float price = booking.getPrice();
						float additionnalPrice = (priceSecondClass * nombrePassagers);
						price += additionnalPrice;
						booking.setPrice(price);
						JOptionPane.showMessageDialog(topFrame, "Le prix des billets est de : " + df.format(additionnalPrice) +"€\nLe prix total est de : " + df.format(booking.getPrice())+"€");
					}				
				}
				else if (classe.equals("")) {
					JOptionPane.showMessageDialog(topFrame, "Veuillez choisir une classe");
				}
				
				if(topFrame.getName().equals("DepartureFrame")){
					booking.setIdFlightDeparture(idFlight);
					topFrame.dispose();
					BookingFlightReturnFrame frame = new BookingFlightReturnFrame(booking);
					frame.setVisible(true);
				}
				else if(topFrame.getName().equals("ReturnFrame")){
					booking.setIdFlightReturn(idFlight);
					topFrame.dispose();
					BookingRoomFrame frame = new BookingRoomFrame(booking);
					frame.setVisible(true);
				}
				
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez renseigner tous les champs");
			}
		}
		else if(e.getSource() == cancelButton){
			topFrame.dispose();
			MainMenuFrame frame = new MainMenuFrame();
			frame.setVisible(true);
		}
		else if(e.getSource() == choixClasse){
			classe = (String) choixClasse.getSelectedItem();
		}
	}

	@Override
	public void onBookingFlightSelection(Flight flight, JTable table) {
		if (flight != null) {
			idFlight = flight.getId();
			priceFirstClass = flight.getPriceFirstClass();
			priceSecondClass = flight.getPriceSecondClass();
		}
		tableSelected = table;
	}
}
