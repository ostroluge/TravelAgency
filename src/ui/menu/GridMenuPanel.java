package ui.menu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ui.category.CategoryFrame;
import ui.city.CityFrame;
import ui.customer.CustomerFrame;
import ui.flight.FlightFrame;
import ui.hotel.HotelFrame;
import ui.line.LineFrame;
import ui.room.RoomFrame;

@SuppressWarnings("serial")
public class GridMenuPanel extends JPanel implements ActionListener {

	protected JButton travelManagementButton;
	protected JButton customerManagementButton;
	protected JButton cityManagementButton;
	protected JButton hotelManagementButton;
	protected JButton categoryManagementButton;
	protected JButton roomManagementButton;
	protected JButton lineManagementButton;
	protected JButton linePlanningManagementButton;
	
	public GridMenuPanel() {
		setButtons();
		setPanel();
	}
	
	private void setButtons() {
		travelManagementButton = new JButton("Gestion des voyages");
		customerManagementButton = new JButton("Gestion des clients");
		cityManagementButton = new JButton("Gestion des villes");
		hotelManagementButton = new JButton("Gestion des hotels");
		categoryManagementButton = new JButton("Gestion des categories");
		roomManagementButton = new JButton("Gestion des chambres");
		lineManagementButton = new JButton("Gestion des lignes");
		linePlanningManagementButton = new JButton("Gestion du planning");
		
		travelManagementButton.addActionListener(this);
		customerManagementButton.addActionListener(this);
		cityManagementButton.addActionListener(this);
		hotelManagementButton.addActionListener(this);
		categoryManagementButton.addActionListener(this);
		roomManagementButton.addActionListener(this);
		lineManagementButton.addActionListener(this);
		linePlanningManagementButton.addActionListener(this);
	}
	
	private void setPanel() {
		setLayout(new GridLayout(4, 2));
		add(travelManagementButton);
		add(customerManagementButton);
		add(cityManagementButton);
		add(hotelManagementButton);
		add(categoryManagementButton);
		add(roomManagementButton);
		add(lineManagementButton);
		add(linePlanningManagementButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		if (e.getSource() == travelManagementButton) {
			JOptionPane.showMessageDialog(topFrame, "Pas implémenté");
		} else if (e.getSource() == customerManagementButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			CustomerFrame customerFrame = new CustomerFrame();
			customerFrame.setVisible(true);
		} else if (e.getSource() == cityManagementButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			CityFrame cityFrame = new CityFrame();
			cityFrame.setVisible(true);
		} else if (e.getSource() == hotelManagementButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			HotelFrame hotelFrame = new HotelFrame();
			hotelFrame.setVisible(true);
		} else if (e.getSource() == categoryManagementButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			CategoryFrame categoryFrame = new CategoryFrame();
			categoryFrame.setVisible(true);
		} else if (e.getSource() == roomManagementButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			RoomFrame roomFrame = new RoomFrame();
			roomFrame.setVisible(true);
		} else if (e.getSource() == lineManagementButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			LineFrame lineFrame = new LineFrame();
			lineFrame.setVisible(true);
		} else if (e.getSource() == linePlanningManagementButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			FlightFrame flightFrame = new FlightFrame();
			flightFrame.setVisible(true);
		}
	}
}
