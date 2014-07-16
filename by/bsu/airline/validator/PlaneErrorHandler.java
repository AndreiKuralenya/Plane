package by.bsu.airline.validator;

import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.filechooser.FileFilter;

import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class PlaneErrorHandler extends DefaultHandler {

	private Logger LOG = Logger.getLogger("by.bsu.airline");

	public PlaneErrorHandler(String log) throws IOException {

		LOG.addAppender(new FileFilter(new SimpleLayout(), log));
	}

	public void warning(SAXParseException e) {
		LOG.warning(getLineAddress(e) + "-" + e.getMessage());
	}

	public void error(SAXParseException e) {
		LOG.warning(getLineAddress(e) + " - " + e.getMessage());
	}

	public void fatalError(SAXParseException e) {
		LOG.warning(getLineAddress(e) + " - " + e.getMessage());
	}

	private String getLineAddress(SAXParseException e) {
		// определение строки и столбца ошибки
		return e.getLineNumber() + " : " + e.getColumnNumber();
	}
}