package job.line;

import factory.LineFactory;

public class DeleteLine {

	public DeleteLine(Long idLine) {
		LineFactory.getInstance().removeLine(idLine);
	}
}
