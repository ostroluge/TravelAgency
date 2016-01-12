package job.line;

import factory.LineFactory;

public class LineManager {

	public static LineManager INSTANCE = new LineManager();
	
	public void addLine(Long idDepartureCity, Long idArrivalCity){
		LineFactory.getInstance().addLine(idDepartureCity, idArrivalCity);
	}
	
	public void deleteLine(Long idLine){
		LineFactory.getInstance().removeLine(idLine);
	}
}
