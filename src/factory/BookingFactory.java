package factory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Booking;
import db.DbManager;

public class BookingFactory {

	private static BookingFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;
	
	public static BookingFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new BookingFactory();
		}
		return INSTANCE;
	}
	
	public Booking create(Long idClient, Long idFlight, Long idCategory, Long idHotel, Long idCityDeparture, Long idCityArrival, int roomNumber, int nombrePassagers, Date dateDeparture, Date dateReturn, float price){
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
				Date dateDeparture = resultPreparedStatement.getDate(9);
				Date dateReturn = resultPreparedStatement.getDate(10);
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
}
