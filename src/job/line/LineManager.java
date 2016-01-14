package job.line;

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
}
