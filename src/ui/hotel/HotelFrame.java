package ui.hotel;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.category.HotelTable;


@SuppressWarnings("serial")
public class HotelFrame extends JFrame{

	JPanel container = new JPanel();
	CityHotelTableContainer table = new CityHotelTableContainer();
	HotelManagementPanel hotelManagementPanel = new HotelManagementPanel();
	
	public HotelFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 400);
		setResizable(false);
		setTitle("Liste des hotels");
		setLocationRelativeTo(null);
		
		initContainer();
		setContentPane(container);
	}

	private void initContainer() {
		container.setLayout(new GridLayout(2,1));
		container.add(table);
		container.add(hotelManagementPanel);
	}
}
