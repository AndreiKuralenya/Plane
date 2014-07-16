package by.bsu.airline.sax;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.SAXException;
import java.io.IOException;

public class SAXSimpleMain {
	public static void main(String[] args) {
		try {
			// создание SAX-анализатора
			XMLReader reader = XMLReaderFactory.createXMLReader();
			SimplePlaneHandler handler = new SimplePlaneHandler();
			reader.setContentHandler(handler);
			reader.parse("src/by/bsu/airline/Plane.xml");
		} catch (SAXException e) {
			System.err.print("ошибка SAX парсера " + e);
		} catch (IOException e) {
			System.err.print("ошибка I/О потока " + e);
		}
	}
}