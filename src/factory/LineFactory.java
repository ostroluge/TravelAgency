package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Line;
import ui.listener.line.LineChangeListener;
import db.DbManager;

public class LineFactory {

	private static LineFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;
	
	private static List<LineChangeListener> listeners = new ArrayList<>();
	
	public static LineFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LineFactory();
		}
		return INSTANCE;
	}

	public List<Line> getAllLines() {
		List<Line> lines = new ArrayList<>();
		try {
			preparedStatement = conn.prepareStatement(
					"select * from line");
			preparedStatement.clearParameters();
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();
		
			while (resultPreparedStatement.next()) {
				Long idLine = resultPreparedStatement.getLong(1);
				Long idDeparture = resultPreparedStatement.getLong(2);
				Long idArrival = resultPreparedStatement.getLong(3);
				lines.add(new Line(idLine, idDeparture, idArrival));
			}
		
			if (lines != null) {
				return lines;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Line getLineById(Long id) {
		Line line = null;
		try {
			preparedStatement = conn.prepareStatement(
					"select * from line where id_line = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Long idLine = resultSet.getLong(1);
				Long idDeparture = resultSet.getLong(2);
				Long idArrival = resultSet.getLong(3);
				line = new Line(idLine, idDeparture, idArrival);
			}
		
			if (line != null) {
				return line;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int addLine(Long idDepartureCity, Long idArrivalCity) {
		try {
			preparedStatement = conn.prepareStatement(
					"insert into line (id_departure_city, id_arrival_city) values (?, ?)");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, idDepartureCity);
			preparedStatement.setLong(2, idArrivalCity);
			
			int resultCode = preparedStatement.executeUpdate();
		
			fireModelChangeEvent();
			
			return resultCode;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int removeLine(Long id) {
		try {
			preparedStatement = conn.prepareStatement(
					"delete from line where id_line = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, id);
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void addListener(LineChangeListener listener) {
		listeners.add(listener);
	}

	private static void fireModelChangeEvent() {
		for (LineChangeListener listener : listeners) {
			listener.lineHasChanged();
		}
	}

	public boolean isAlreadyExisting(Long idDeparture, Long idArrival) {
		ResultSet resultSet;
		int numberRow = 0;
		try {
			preparedStatement = conn.prepareStatement(
					"select count(*) as ROWCOUNT from line where id_departure_city = ? and id_arrival_city = ?");
			preparedStatement.clearParameters();
			preparedStatement.setLong(1, idDeparture);
			preparedStatement.setLong(2, idArrival);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			numberRow = resultSet.getInt("rowcount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(numberRow >= 1){
			return true;
		}
		else{
			return false;
		}
	}
}
