package ui.category;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CategoryFrame extends JFrame {

	JPanel container = new JPanel();
	HotelCategoryTableContainer tableContainer = new HotelCategoryTableContainer();
	CategoryManagementPanel managementPanel = new CategoryManagementPanel();
	
	public CategoryFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 400);
		setResizable(false);
		setTitle("Les categories");
		setLocationRelativeTo(null);
		
		initContainer();
		setContentPane(container);
	}

	private void initContainer() {
		container.setLayout(new GridLayout(2,1));
		container.add(tableContainer);
		container.add(managementPanel);
	}
}
