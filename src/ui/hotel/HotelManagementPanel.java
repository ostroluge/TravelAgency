package ui.hotel;

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

import job.hotel.HotelManager;
import model.City;
import model.Hotel;
import ui.listener.city.CityHotelSelectionListener;
import ui.listener.hotel.HotelCitySelectionListener;
import ui.menu.MainMenuFrame;

@SuppressWarnings("serial")
public class HotelManagementPanel extends JPanel implements ActionListener, HotelCitySelectionListener, CityHotelSelectionListener{

	protected JLabel labelHotelName = new JLabel();
	
	protected JTextField hotelName;
	
	protected JButton addButton;
	protected JButton deleteButton;
	protected JButton clearButton;
	protected JButton returnButton;
	
	protected City citySelected;
	protected Hotel hotelSelected;
	protected JTable tableSelected;
	
	public HotelManagementPanel() {
		HotelCityTable.INSTANCE.addListener(this);
		CityHotelTable.INSTANCE.addListener(this);
		setLabels();
		setTextFields();
		setButtons();
		setPanel();
	}

	private void setLabels() {
		labelHotelName.setText("Nom de l'hotel :");
	}

	private void setTextFields() {
		Document modelTextFieldln = new PlainDocument();
		hotelName = new JTextField(modelTextFieldln, "", 10);
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
		setLayout(new GridLayout(3,4));
		add(labelHotelName);
		add(hotelName);
		add(addButton);
		add(clearButton);
		add(deleteButton);
		add(returnButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		if (e.getSource() == addButton) {
			if (!hotelName.getText().trim().equals("")) {
				HotelManager.INSTANCE.addHotel(citySelected.getId(),hotelName.getText());
			} else {
				 JOptionPane.showMessageDialog(topFrame, "Veuillez renseigner tous les champs");
			}
		} else if (e.getSource() == deleteButton) {
			if (hotelSelected != null) {
				HotelManager.INSTANCE.deleteHotel(hotelSelected.getId());
				clearSelection();
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez sélectionner un hôtel à supprimer");
			}
		} else if (e.getSource() == clearButton) {
				if(hotelSelected != null){
					clearSelection();
				}
				else{
					hotelName.setText("");
				}
			if (hotelSelected != null) {
				clearSelection();
			} else {
				hotelName.setName("");
			}
		} else if (e.getSource() == returnButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			MainMenuFrame frame = new MainMenuFrame();
			frame.setVisible(true);
		}
	}

	private void clearSelection() {
		hotelSelected = null;
		tableSelected.clearSelection();
		hotelName.setText("");
	}

	@Override
	public void onCitySelection(City city) {
		if(city != null){
			citySelected = city;
		}		
	}

	@Override
	public void onHotelSelection(Hotel hotel, JTable table) {
		if (hotel != null) {
			hotelSelected = hotel;
			hotelName.setText(hotel.getName());
		}
		tableSelected = table;
	}
}

