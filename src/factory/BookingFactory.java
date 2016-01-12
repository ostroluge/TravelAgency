package factory;

public class BookingFactory {

	private static BookingFactory INSTANCE;
	
	public static BookingFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new BookingFactory();
		}
		return INSTANCE;
	}
}
