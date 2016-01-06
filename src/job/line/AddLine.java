package job.line;

import factory.LineFactory;

public class AddLine {

	public AddLine(Long idDepartureCity, Long idArrivalCity) {
		LineFactory.getInstance().addLine(idDepartureCity, idArrivalCity);
	}
}
