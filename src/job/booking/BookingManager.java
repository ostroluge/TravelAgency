package job.booking;

import factory.BookingFactory;
import model.Booking;

public class BookingManager {

	public static BookingManager INSTANCE = new BookingManager();
	
	public void addBooking(Booking booking) {
		BookingFactory.getInstance().addBooking(booking);
	}

	public void cancelBooking(Long bookingId) {
		BookingFactory.getInstance().cancelBooking(bookingId);
	}
}
