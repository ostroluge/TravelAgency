package job.flight;

import java.util.List;

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
	
	/**
	 * Récupère les vols à partir de leur idLine
	 * @param idLine
	 * @return List<Flight>
	 */
	public List<Flight> getFlightsByLineId(Long idLine) {
		return FlightFactory.getInstance().getFlightsByLineId(idLine);
	}
	
	/**
	 * Récupère vol à partir de son id
	 * @param id
	 * @return Flight
	 */
	public Flight getFlightById(Long id) {
		return FlightFactory.getInstance().getFlightById(id);
	}
	
	/**
	 * Récupère les vols à partir de leur idCityDeparture
	 * @param id
	 * @return List<Flight>
	 */
	public List<Flight> getFlightByIdCityDeparture(Long id) {
		return FlightFactory.getInstance().getFlightByIdCityDeparture(id);
	}
}
