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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import job.booking.BookingManager;
import model.Booking;
import model.Flight;
import ui.listener.booking.BookingFlightSelectionListener;
import ui.menu.MainMenuFrame;

@SuppressWarnings("serial")
public class BookingFlightManagementPanel extends JPanel implements ActionListener, BookingFlightSelectionListener{

	JLabel labelChoixClasse = new JLabel();
	JComboBox<Object> choixClasse;
	protected Booking booking; 
	
	protected Integer classe;
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
		List<Integer> choixClasse = new ArrayList<>();
		choixClasse.add(1);
		choixClasse.add(2);
		
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
		if(e.getSource() == validationButton){
			if(classe != null && idFlight != null && priceFirstClass != null && priceSecondClass != null){
				booking.setIdFlight(idFlight);
				int nombrePassagers = booking.getNombrePassagers();
				if(classe.equals(1)){
					booking.setPrice(priceFirstClass * nombrePassagers);
				}
				else if(classe.equals(2)){
					booking.setPrice(priceSecondClass * nombrePassagers);
				}
				System.out.println("Price :" + booking.getPrice());
			}
		}
		else if(e.getSource() == cancelButton){
			topFrame.dispose();
			MainMenuFrame frame = new MainMenuFrame();
			frame.setVisible(true);
		}
		else if(e.getSource() == choixClasse){
			classe = (Integer) choixClasse.getSelectedItem();
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
