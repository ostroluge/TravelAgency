package ui.room;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RoomFrame extends JFrame {

	JPanel container = new JPanel();
	RoomCategoryTableContainer tableContainer = new RoomCategoryTableContainer();
	RoomManagementPanel managementPanel = new RoomManagementPanel();
	
	public RoomFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 400);
		setResizable(false);
		setTitle("Les chambres");
		setLocationRelativeTo(null);
		
		initContainer();
		setContentPane(container);
	}

	private void initContainer() {
		container.setLayout(new GridLayout(2, 1));
		container.add(tableContainer);
		container.add(managementPanel);
	}
}
