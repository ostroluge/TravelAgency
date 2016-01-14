package job.flight;

import factory.FlightFactory;
import model.Flight;

/**
 * Classe permettant de gerer les vols
 */
public class FlightManager {

	public static FlightManager INSTANCE = new FlightManager();
	
	/**
	 * Ajoute un vol en base de donnees
	 * @param flight
	 */
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

	/**
	 * Supprimer un vol en base de donnees
	 * @param id
	 */
	public void removeFlight(Long id) {
		FlightFactory.getInstance().removeFlight(id);
	}

	/**
	 * Modifie un vol en base de donnees
	 * @param id
	 * @param flight
	 */
	public void editFlight(Long id, Flight flight) {
		FlightFactory.getInstance().editFlight(id, flight);
	}
}
