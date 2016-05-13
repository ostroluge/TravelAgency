package job.booking;

import java.util.List;

import model.Booking;
import factory.BookingFactory;

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
	
	/**
	 * Récupère la liste de booking via l'id client
	 * @param idCustomer
	 * @return List<Booking>
	 */
	public List<Booking> getBookingByClientId(Long idCustomer){
		return BookingFactory.getInstance().getBookingByClientId(idCustomer);
	}
	
	/**
	 * Récupère la réservation par son id
	 * @param id
	 * @return Booking
	 */
	public Booking getBookingById(Long id){
		return BookingFactory.getInstance().getBookingById(id);
	}
}
