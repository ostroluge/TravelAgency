package job.line;

import java.util.List;

import model.Line;
import factory.LineFactory;

/**
 * Classe permettant de gerer les lignes 
 */
public class LineManager {

	public static LineManager INSTANCE = new LineManager();
	
	/**
	 * Ajout d'une ligne en base de donnees
	 * @param idDepartureCity
	 * @param idArrivalCity
	 */
	public void addLine(Long idDepartureCity, Long idArrivalCity){
		LineFactory.getInstance().addLine(idDepartureCity, idArrivalCity);
	}
	
	/**
	 * Suppression d'une ligne en base de donnees
	 * @param idLine
	 */
	public void deleteLine(Long idLine){
		LineFactory.getInstance().removeLine(idLine);
	}
	
	public List<Line> getAllLines() {
		return LineFactory.getInstance().getAllLines();
	}
	
	public Line getLineById(Long id) {
		return LineFactory.getInstance().getLineById(id);
	}
}
