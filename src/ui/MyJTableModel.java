package ui;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MyJTableModel extends DefaultTableModel {

	public MyJTableModel(Object[] columns, int rowNumber) {
		super(columns, rowNumber);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
