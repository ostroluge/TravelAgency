package ui.line;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LineFrame extends JFrame {

	JPanel container = new JPanel();
	LineTable table = new LineTable();
	LineManagementPanel managementPanel = new LineManagementPanel();
	
	public LineFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 400);
		setResizable(false);
		setTitle("Liste des lignes");
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
