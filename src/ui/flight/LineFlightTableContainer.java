package ui.flight;

import java.awt.GridLayout;

import javax.swing.JPanel;

import ui.line.LineTable;

@SuppressWarnings("serial")
public class LineFlightTableContainer extends JPanel {

	LineTable lineTable = new LineTable();
	FlightTable flightTable = new FlightTable();
	
	public LineFlightTableContainer() {
		setLayout(new GridLayout(1, 2));
		add(lineTable);
		add(flightTable);
	}
}
