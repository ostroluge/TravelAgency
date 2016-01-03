package ui.menu;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenuFrame extends JFrame {

	JPanel container = new JPanel();
	GridMenuPanel gridMenuPanel = new GridMenuPanel();
	
	public MainMenuFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 150);
		setResizable(false);
		setTitle("Menu principal");
		setLocationRelativeTo(null);
		
		initContainer();
		setContentPane(container);
	}

	private void initContainer() {
		container.add(gridMenuPanel);
	}
}
