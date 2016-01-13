package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Booking;
import ui.listener.booking.BookingChangeListener;
import db.DbManager;

public class BookingFactory {

	private static BookingFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;
	
	private static List<BookingChangeListener> listeners = new ArrayList<>();
	
	public static BookingFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new BookingFactory();
		}
		return INSTANCE;
	}
	
	public Booking create(Long idClient, Long idFlight, Long idCategory, Long idHotel, Long idCityDeparture, Long idCityArrival, int roomNumber, int nombrePassagers, String dateDeparture, String dateReturn, float price){
		return new Booking(idClient, idFlight, idCategory, idHotel, idCityDeparture, idCityArrival, roomNumber, nombrePassagers, dateDeparture,dateReturn, price);
	}
	
	public List<Booking> getBookingByClientId(Long idCustomer){
		List<Booking> bookings = new ArrayList<>();
		try{
			preparedStatement = conn.prepareStatement("select * from booking where id_client = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, idCustomer);
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();
			
			while(resultPreparedStatement.next()){
				Long id = resultPreparedStatement.getLong(1);
				Long idClient = resultPreparedStatement.getLong(2);
				Long idFlight = resultPreparedStatement.getLong(3);
				Long idCategory = resultPreparedStatement.getLong(4);
				Long idHotel = resultPreparedStatement.getLong(5);
				int roomNumber = resultPreparedStatement.getInt(6);
				Long idCityDeparture = resultPreparedStatement.getLong(7);
				Long idCityArrival = resultPreparedStatement.getLong(8);
				String dateDeparture = resultPreparedStatement.getString(9);
				String dateReturn = resultPreparedStatement.getString(10);
				int nombrePassagers = resultPreparedStatement.getInt(11);
				float price = resultPreparedStatement.getFloat(12);
				
				bookings.add(new Booking(id,idClient,idFlight,idCategory, idHotel, idCityDeparture, idCityArrival, roomNumber, nombrePassagers, dateDeparture, dateReturn,price));
			}
			
			if(!bookings.isEmpty()){
				return bookings;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}

	public Booking getBookingById(Long id) {
		Booking booking = null;
		try {
			preparedStatement = conn.prepareStatement(
					"select * from booking where id_booking = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Long idBooking = resultSet.getLong(1);
				Long idClient = resultSet.getLong(2);
				Long idFlight = resultSet.getLong(3);
				Long idCategory = resultSet.getLong(4);
				Long idHotel = resultSet.getLong(5);
				int roomNumber = resultSet.getInt(6);
				Long idCityDeparture = resultSet.getLong(7);
				Long idCityArrival = resultSet.getLong(8);
				String dateDeparture = resultSet.getString(9);
				String dateReturn = resultSet.getString(10);
				int nombrePassagers = resultSet.getInt(11);
				float price = resultSet.getFloat(12);
				
				booking = new Booking(idBooking,idClient,idFlight,idCategory, idHotel, idCityDeparture,
						idCityArrival, roomNumber, nombrePassagers, dateDeparture, dateReturn,price);
			}
			
			if (booking != null) {
				return booking;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int addBooking(Booking booking) {
		try {
			preparedStatement = conn.prepareStatement(
					"insert into booking (id_client, id_city_departure,"
					+ "id_city_arrival, date_departure, date_return, nb_passager)"
					+ "values (?, ?, ?, ?, ?, ?)");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, booking.getIdClient());
			preparedStatement.setLong(2, booking.getIdCityDeparture());
			preparedStatement.setLong(3, booking.getIdCityArrival());
			preparedStatement.setString(4, booking.getDateDeparture());
			preparedStatement.setString(5, booking.getDateReturn());
			preparedStatement.setInt(6, booking.getNombrePassagers());
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int cancelBooking(Long idBooking) {
		try {
			preparedStatement = conn.prepareStatement(
					"delete from booking where id_booking = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, idBooking);
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void addListener(BookingChangeListener listener) {
		listeners.add(listener);
	}

	private static void fireModelChangeEvent() {
		for (BookingChangeListener listener : listeners) {
			listener.bookingHasChanged();
		}
	}
}
