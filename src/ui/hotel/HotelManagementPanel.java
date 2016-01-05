package ui.hotel;

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

import job.hotel.AddHotel;
import job.hotel.DeleteHotel;
import model.City;
import model.Hotel;
import ui.category.HotelTable;
import ui.city.CityTable;
import ui.listener.city.CityHotelSelectionListener;
import ui.listener.hotel.HotelCitySelectionListener;

@SuppressWarnings("serial")
public class HotelManagementPanel extends JPanel implements ActionListener, HotelCitySelectionListener, CityHotelSelectionListener{

	protected JLabel labelHotelName = new JLabel();
	protected JLabel labelIdVille = new JLabel();
	
	protected JTextField hotelName;
	protected JTextField idVille;
	
	protected JButton addButton;
	protected JButton deleteButton;
	protected JButton clearButton;
	
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
		labelIdVille.setText("Id de la ville :");
	}

	private void setTextFields() {
		Document modelTextFieldln = new PlainDocument();
		Document modelTextFieldbd = new PlainDocument();
		hotelName = new JTextField(modelTextFieldln, "", 10);
		idVille = new JTextField(modelTextFieldbd, "", 10);
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
		setLayout(new GridLayout(3,4));
		add(labelHotelName);
		add(hotelName);
		add(labelIdVille);
		add(idVille);
		add(addButton);
		add(clearButton);
		add(deleteButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			if (!hotelName.getText().trim().equals("") &&
					!idVille.getText().trim().equals("")) {
				new AddHotel(Long.parseLong(idVille.getText()),hotelName.getText());
			} else {
				System.out.println("Veuillez renseigner tous les champs");
			}
		} else if (e.getSource() == deleteButton) {
			if (hotelSelected != null) {
				new DeleteHotel(hotelSelected.getId());
				clearSelection();
			} else {
				System.out.println("Veuillez selectionner un hotel a supprimer");
			}
		} else if (e.getSource() == clearButton) {
				clearSelection();
		}
	}

	private void clearSelection() {
		hotelSelected = null;
		tableSelected.clearSelection();
		hotelName.setText("");
		idVille.setText("");
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
			idVille.setText(hotel.getIdCity().toString());
		}
		tableSelected = table;
	}
}

