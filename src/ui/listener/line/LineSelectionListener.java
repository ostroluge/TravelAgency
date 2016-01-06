package ui.listener.line;

import javax.swing.JTable;

import model.Line;

public interface LineSelectionListener {

	public void onLineSelection(Line line, JTable table);
}
