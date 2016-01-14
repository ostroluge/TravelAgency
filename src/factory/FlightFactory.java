package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Flight;
import ui.listener.flight.FlightChangeListener;
import db.DbManager;

public class FlightFactory {

	private static FlightFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;

	private static List<FlightChangeListener> listeners = new ArrayList<>();
	
	public static FlightFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FlightFactory();
		}
		return INSTANCE;
	}

	public List<Flight> getFlightsByLineId(Long idLine) {
		List<Flight> flights = new ArrayList<>();
		try {
			preparedStatement = conn.prepareStatement(
					"select * from flight where id_line = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, idLine);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Long id = resultSet.getLong(1);
				Long idL = resultSet.getLong(2);
				String dayOfWeek = resultSet.getString(3);
				String departureTime = resultSet.getString(4);
				int flightDuration = resultSet.getInt(5);
				int maxPassengerFirstClass = resultSet.getInt(6);
				float priceFirstClass = resultSet.getFloat(7);
				int maxPassengerSecondClass = resultSet.getInt(8);
				float priceSecondClass = resultSet.getFloat(9);
				int cancellationTime = resultSet.getInt(10);
				flights.add(new Flight(id, idL, dayOfWeek,
						departureTime, flightDuration, maxPassengerFirstClass,
						priceFirstClass, maxPassengerSecondClass, priceSecondClass,
						cancellationTime));
			}
		
			if (flights != null) {
				return flights;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Flight getFlightById(Long id) {
		Flight flight = null;
		try {
			preparedStatement = conn.prepareStatement(
					"select * from flight where id_flight = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Long idFlight = resultSet.getLong(1);
				Long idLine = resultSet.getLong(2);
				String dayOfWeek = resultSet.getString(3);
				String departureTime = resultSet.getString(4);
				int flightDuration = resultSet.getInt(5);
				int maxPassengerFirstClass = resultSet.getInt(6);
				float priceFirstClass = resultSet.getFloat(7);
				int maxPassengerSecondClass = resultSet.getInt(8);
				float priceSecondClass = resultSet.getFloat(9);
				int cancellationTime = resultSet.getInt(10);
				flight = new Flight(idFlight, idLine, dayOfWeek,
						departureTime, flightDuration, maxPassengerFirstClass,
						priceFirstClass, maxPassengerSecondClass, priceSecondClass,
						cancellationTime);
			}
		
			if (flight != null) {
				return flight;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Flight> getFlightByIdCityDeparture(Long id) {
		List<Flight> listFlight = new ArrayList<Flight>();
		Flight flight= null;
		try {
			preparedStatement = conn.prepareStatement(
					"select * from flight where id_line in (select id_line from line where id_departure_city = ?)");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Long idFlight = resultSet.getLong(1);
				Long idLine = resultSet.getLong(2);
				String dayOfWeek = resultSet.getString(3);
				String departureTime = resultSet.getString(4);
				int flightDuration = resultSet.getInt(5);
				int maxPassengerFirstClass = resultSet.getInt(6);
				float priceFirstClass = resultSet.getFloat(7);
				int maxPassengerSecondClass = resultSet.getInt(8);
				float priceSecondClass = resultSet.getFloat(9);
				int cancellationTime = resultSet.getInt(10);
				flight = new Flight(idFlight, idLine, dayOfWeek,
						departureTime, flightDuration, maxPassengerFirstClass,
						priceFirstClass, maxPassengerSecondClass, priceSecondClass,
						cancellationTime);
				listFlight.add(flight);
			}
		
			if (listFlight != null) {
				return listFlight;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int addFlight(Long idLine, String dayOfWeek, String departureTime,
			int flightDuration, int maxPassengerFirstClass, float pricePassengerFirstClass,
			int maxPassengerSecondClass, float priceSecondClass, int cancellationTime) {
		try {
			preparedStatement = conn.prepareStatement(
					"insert into flight (id_line, day_of_week, departure_time,"
					+ "flight_duration, max_passenger_first_class, price_first_class,"
					+ "max_passenger_second_class, price_second_class, cancellation_time)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, idLine);
			preparedStatement.setString(2, dayOfWeek);
			preparedStatement.setString(3, departureTime);
			preparedStatement.setInt(4, flightDuration);
			preparedStatement.setInt(5, maxPassengerFirstClass);
			preparedStatement.setFloat(6, pricePassengerFirstClass);
			preparedStatement.setInt(7, maxPassengerSecondClass);
			preparedStatement.setFloat(8, priceSecondClass);
			preparedStatement.setInt(9, cancellationTime);
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int editFlight(Long id, Flight flight) {
		try {
			preparedStatement = conn.prepareStatement(
					"update flight set day_of_week = ?, "
					+ "departure_time = ?, flight_duration = ?, "
					+ "max_passenger_first_class = ?, price_first_class = ?, "
					+ "max_passenger_second_class = ?, price_second_class = ?, "
					+ "cancellation_time = ? where id_flight = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, flight.getDayOfWeek());
			preparedStatement.setString(2, flight.getDepartureTime());
			preparedStatement.setInt(3, flight.getFlightDuration());
			preparedStatement.setInt(4, flight.getMaxPassengerFirstClass());
			preparedStatement.setFloat(5, flight.getPriceFirstClass());
			preparedStatement.setInt(6, flight.getMaxPassengerSecondClass());
			preparedStatement.setFloat(7, flight.getPriceSecondClass());
			preparedStatement.setInt(8, flight.getCancellationTime());
			preparedStatement.setLong(9, id);
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int removeFlight(Long id) {
		try {
			preparedStatement = conn.prepareStatement(
					"delete from flight where id_flight = ?");
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

	public void addListener(FlightChangeListener listener) {
		listeners.add(listener);
	}

	private static void fireModelChangeEvent() {
		for (FlightChangeListener listener : listeners) {
			listener.flightHasChanged();
		}
	}
}
