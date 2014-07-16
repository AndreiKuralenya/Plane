package by.bsu.airline.sax;

import by.bsu.airline.model.Plane;
import java.io.IOException;
import java.util.Set;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class PlanesSAXBuilder {
	private Set<Plane> planes;
	private PlaneHandler ph;
	private XMLReader reader;

	public PlanesSAXBuilder() {
		// создание SAX-анализатора
		ph = new PlaneHandler();
		try {
			// создание объекта-обработчика
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(ph);
		} catch (SAXException e) {
			System.err.print("ошибка SAX парсера: " + e);
		}
	}

	public Set<Plane> getStudents() {
		return planes;
	}

	public void buildSetStudents(String fileName) {
		try {
			// разбор XML-документа
			reader.parse(fileName);
		} catch (SAXException e) {
			System.err.print("ошибка SAX парсера: " + e);
		} catch (IOException e) {
			System.err.print("ошибка I/О потока: " + e);
		}
		planes = ph.getPlanes();
	}
}