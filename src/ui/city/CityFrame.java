package ui.city;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CityFrame extends JFrame {

	JPanel container = new JPanel();
	CityTable table = new CityTable();
	CityManagementPanel cityManagementPanel = new CityManagementPanel();
	
	public CityFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 400);
		setResizable(false);
		setTitle("Liste des villes");
		setLocationRelativeTo(null);
		
		initContainer();
		setContentPane(container);
	}

	private void initContainer() {
		container.setLayout(new GridLayout(2,1));
		container.add(table);
		container.add(cityManagementPanel);
	}
}
