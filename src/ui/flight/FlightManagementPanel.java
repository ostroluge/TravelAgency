package ui.flight;

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

import job.flight.FlightManager;
import model.Flight;
import model.Line;
import ui.line.LineTable;
import ui.listener.flight.FlightSelectionListener;
import ui.listener.line.LineSelectionListener;
import ui.menu.MainMenuFrame;

@SuppressWarnings("serial")
public class FlightManagementPanel extends JPanel implements ActionListener, FlightSelectionListener, LineSelectionListener {

	protected JLabel labelDay = new JLabel();
	protected JLabel labelDepartureTime = new JLabel();
	protected JLabel labelFlightDuration = new JLabel();
	protected JLabel labelMaxPassenger1stClass = new JLabel();
	protected JLabel labelPrice1stClass = new JLabel();
	protected JLabel labelMaxPassenger2ndClass = new JLabel();
	protected JLabel labelPrice2ndClass = new JLabel();
	protected JLabel labelCancellationTime = new JLabel();
	
	protected JTextField dayOfWeek;
	protected JTextField departureTime;
	protected JTextField flightDuration;
	protected JTextField maxPassenger1stClass;
	protected JTextField price1stClass;
	protected JTextField maxPassenger2ndClass;
	protected JTextField price2ndClass;
	protected JTextField cancellationTime;
	
	protected JButton addButton;
    protected JButton editButton;
    protected JButton deleteButton;
    protected JButton clearButton;
	protected JButton returnButton;
	
	protected Flight flightSelected;
	protected Line lineSelected;
	protected JTable tableSelected;
	
	public FlightManagementPanel() {
		FlightTable.INSTANCE.addListener(this);
		LineTable.INSTANCE.addListener(this);
		setLabels();
		setTextFields();
		setButtons();
		setPanel();
	}
	
	private void setLabels() {
		labelDay.setText("Jour de la semaine :");
		labelDepartureTime.setText("Heure de départ :");
		labelFlightDuration.setText("Durée du vol :");
		labelMaxPassenger1stClass.setText("Places 1ère classe :");
		labelPrice1stClass.setText("Prix 1ère classe :");
		labelMaxPassenger2ndClass.setText("Places 2nde classe :");
		labelPrice2ndClass.setText("Prix 2nde classe :");
		labelCancellationTime.setText("Annulation (jours) :");
	}
	
	private void setTextFields() {
		Document modelDay = new PlainDocument();
		Document modelDTime = new PlainDocument();
		Document modelFlightDuration = new PlainDocument();
		Document modelMaxPass1stClass = new PlainDocument();
		Document modelPrice1stClass = new PlainDocument();
		Document modelPass2ndClass = new PlainDocument();
		Document modelPrice2ndClass = new PlainDocument();
		Document modelCancellationTime = new PlainDocument();
		dayOfWeek = new JTextField(modelDay, "", 10);
		departureTime = new JTextField(modelDTime, "", 10);
		flightDuration = new JTextField(modelFlightDuration, "", 10);
		maxPassenger1stClass = new JTextField(modelMaxPass1stClass, "", 10);
		price1stClass = new JTextField(modelPrice1stClass, "", 10);
		maxPassenger2ndClass = new JTextField(modelPass2ndClass, "", 10);
		price2ndClass = new JTextField(modelPrice2ndClass, "", 10);
		cancellationTime = new JTextField(modelCancellationTime, "", 10);
	}
	
	private void setButtons() {
		addButton = new JButton("Ajouter");
        editButton = new JButton("Modifier");
        clearButton = new JButton("Clear");
        deleteButton = new JButton("Supprimer");
		returnButton = new JButton("Retour");

        addButton.addActionListener(this);
        editButton.addActionListener(this);
        clearButton.addActionListener(this);
        deleteButton.addActionListener(this);
		returnButton.addActionListener(this);
	}
	
	private void setPanel() {
		setLayout(new GridLayout(6, 4));
		add(labelDay);
		add(dayOfWeek);
		add(labelDepartureTime);
		add(departureTime);
		add(labelFlightDuration);
		add(flightDuration);
		add(labelMaxPassenger1stClass);
		add(maxPassenger1stClass);
		add(labelPrice1stClass);
		add(price1stClass);
		add(labelMaxPassenger2ndClass);
		add(maxPassenger2ndClass);
		add(labelPrice2ndClass);
		add(price2ndClass);
		add(labelCancellationTime);
		add(cancellationTime);
		add(addButton);
        add(editButton);
        add(clearButton);
        add(deleteButton);
        add(returnButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		if (e.getSource() == addButton) {
			if (!dayOfWeek.getText().trim().equals("") && 
				!departureTime.getText().trim().equals("") &&
				!flightDuration.getText().trim().equals("") &&
				!maxPassenger1stClass.getText().trim().equals("") &&
				!price1stClass.getText().trim().equals("") &&
				!maxPassenger2ndClass.getText().trim().equals("") &&
				!price2ndClass.getText().trim().equals("") &&
				!cancellationTime.getText().trim().equals("")) {
				
				Flight flightToAdd = new Flight(dayOfWeek.getText().toString(),
												departureTime.getText().toString(),
												Integer.parseInt(flightDuration.getText().toString()),
												Integer.parseInt(maxPassenger1stClass.getText().toString()),
												Float.parseFloat(price1stClass.getText().toString()),
												Integer.parseInt(maxPassenger2ndClass.getText().toString()),
												Float.parseFloat(price2ndClass.getText().toString()),
												Integer.parseInt(cancellationTime.getText().toString()));
				
				if (lineSelected != null) {
					flightToAdd.setIdLine(lineSelected.getId());
					FlightManager.INSTANCE.addFlight(flightToAdd);
				} else {
					JOptionPane.showMessageDialog(topFrame, "Aucune ligne sélectionnée");
				}
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez renseigner tous les champs");
			}
		} else if (e.getSource() == editButton) {
			if (flightSelected != null) {
				if (!dayOfWeek.getText().trim().equals("") && 
						!departureTime.getText().trim().equals("") &&
						!flightDuration.getText().trim().equals("") &&
						!maxPassenger1stClass.getText().trim().equals("") &&
						!price1stClass.getText().trim().equals("") &&
						!maxPassenger2ndClass.getText().trim().equals("") &&
						!price2ndClass.getText().trim().equals("") &&
						!cancellationTime.getText().trim().equals("")) {
					
					Flight flightToEdit = new Flight(dayOfWeek.getText().toString(),
													 departureTime.getText().toString(),
													 Integer.parseInt(flightDuration.getText().toString()),
													 Integer.parseInt(maxPassenger1stClass.getText().toString()),
													 Float.parseFloat(price1stClass.getText().toString()),
													 Integer.parseInt(maxPassenger2ndClass.getText().toString()),
													 Float.parseFloat(price2ndClass.getText().toString()),
													 Integer.parseInt(cancellationTime.getText().toString()));
				
					if (lineSelected != null) {
						flightToEdit.setIdLine(lineSelected.getId());
						if (flightSelected != null) {
							flightToEdit.setId(flightSelected.getId());
							FlightManager.INSTANCE.editFlight(flightToEdit.getId(), flightToEdit);
						} else {
							JOptionPane.showMessageDialog(topFrame, "Aucun vol sélectionné");
						}
					} else {
						JOptionPane.showMessageDialog(topFrame, "Aucune ligne sélectionnée");
					}
				}
			} else {
				JOptionPane.showMessageDialog(topFrame, "Aucun vol sélectionné");
			}
		} else if (e.getSource() == clearButton) {
			if (flightSelected != null) {
				clearSelection();
			} else {
				dayOfWeek.setText("");
				departureTime.setText("");
				flightDuration.setText("");
				maxPassenger1stClass.setText("");
				price1stClass.setText("");
				maxPassenger2ndClass.setText("");
				price2ndClass.setText("");
				cancellationTime.setText("");
			}
		} else if (e.getSource() == deleteButton) {
			if (flightSelected != null) {
				FlightManager.INSTANCE.removeFlight(flightSelected.getId());
				clearSelection();
			} else {
				JOptionPane.showMessageDialog(topFrame, "Aucun vol sélectionné");
			}
		} else if (e.getSource() == returnButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			MainMenuFrame frame = new MainMenuFrame();
			frame.setVisible(true);
		}
	}

	private void clearSelection() {
		flightSelected = null;
		tableSelected.clearSelection();
		dayOfWeek.setText("");
		departureTime.setText("");
		flightDuration.setText("");
		maxPassenger1stClass.setText("");
		price1stClass.setText("");
		maxPassenger2ndClass.setText("");
		price2ndClass.setText("");
		cancellationTime.setText("");
	}
	
	@Override
	public void onFlightSelection(Flight flight, JTable table) {
		if (flight != null) {
			flightSelected = flight;
			dayOfWeek.setText(flight.getDayOfWeek());
			departureTime.setText(flight.getDepartureTime());
			flightDuration.setText(flight.getFlightDuration()+"");
			maxPassenger1stClass.setText(flight.getMaxPassengerFirstClass()+"");
			price1stClass.setText(flight.getPriceFirstClass()+"");
			maxPassenger2ndClass.setText(flight.getMaxPassengerSecondClass()+"");
			price2ndClass.setText(flight.getPriceSecondClass()+"");
			cancellationTime.setText(flight.getCancellationTime()+"");
		}
		tableSelected = table;
	}
	
	@Override
	public void onLineSelection(Line line, JTable table) {
		if (line != null) {
			lineSelected = line;
		}
	}
}
