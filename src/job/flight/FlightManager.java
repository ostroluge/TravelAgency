package job.flight;

import factory.FlightFactory;
import model.Flight;

public class FlightManager {

	public static FlightManager INSTANCE = new FlightManager();
	
	public void addFlight(Flight flight) {
		FlightFactory.getInstance().addFlight(flight.getIdLine(),
											  flight.getDayOfWeek(),
											  flight.getDepartureTime(),
											  flight.getFlightDuration(),
											  flight.getMaxPassengerFirstClass(),
											  flight.getPriceFirstClass(),
											  flight.getMaxPassengerSecondClass(),
											  flight.getPriceSecondClass(),
											  flight.getCancellationTime());
	}

	public void removeFlight(Long id) {
		FlightFactory.getInstance().removeFlight(id);
	}

	public void editFlight(Long id, Flight flight) {
		FlightFactory.getInstance().editFlight(id, flight);
	}
}
