package ui.flight;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FlightFrame extends JFrame {

	JPanel container = new JPanel();
	LineFlightTableContainer table = new LineFlightTableContainer();
	FlightManagementPanel managementPanel = new FlightManagementPanel();

	public FlightFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 400);
		setResizable(false);
		setTitle("Liste des plannings");
		setLocationRelativeTo(null);
		
		initContainer();
		setContentPane(container);
	}

	private void initContainer() {
		container.setLayout(new GridLayout(2,1));
		container.add(table);
		container.add(managementPanel);
	}
}
