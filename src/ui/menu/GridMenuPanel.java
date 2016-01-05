package ui.menu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.category.CategoryFrame;
import ui.city.CityFrame;
import ui.customer.CustomerFrame;
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
		if (e.getSource() == travelManagementButton) {
			System.out.println("Not implemented yet");
		} else if (e.getSource() == customerManagementButton) {
			CustomerFrame customerFrame = new CustomerFrame();
			customerFrame.setVisible(true);
		} else if (e.getSource() == cityManagementButton) {
			CityFrame cityFrame = new CityFrame();
			cityFrame.setVisible(true);
		} else if (e.getSource() == hotelManagementButton) {
			System.out.println("Not implemented yet");
		} else if (e.getSource() == categoryManagementButton) {
			CategoryFrame categoryFrame = new CategoryFrame();
			categoryFrame.setVisible(true);
		} else if (e.getSource() == roomManagementButton) {
			RoomFrame roomFrame = new RoomFrame();
			roomFrame.setVisible(true);
		} else if (e.getSource() == lineManagementButton) {
			System.out.println("Not implemented yet");
		} else if (e.getSource() == linePlanningManagementButton) {
			System.out.println("Not implemented yet");
		}
	}
}
