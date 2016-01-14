package job.booking;

import factory.BookingFactory;
import model.Booking;

/**
 * Classe permettant de gerer les reservations 
 */
public class BookingManager {

	public static BookingManager INSTANCE = new BookingManager();
	
	/**
	 * Ajoute une reservation a la base de donnees
	 * @param booking
	 */
	public void addBooking(Booking booking) {
		BookingFactory.getInstance().addBooking(booking);
	}

	/**
	 * Annule une reservation
	 * @param bookingId
	 */
	public void cancelBooking(Long bookingId) {
		BookingFactory.getInstance().cancelBooking(bookingId);
	}
}
