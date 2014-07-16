package by.bsu.airline.sax;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.SAXException;
import java.io.IOException;

public class SAXSimpleMain {
	public static void main(String[] args) {
		try {
			// �������� SAX-�����������
			XMLReader reader = XMLReaderFactory.createXMLReader();
			SimplePlaneHandler handler = new SimplePlaneHandler();
			reader.setContentHandler(handler);
			reader.parse("src/by/bsu/airline/Plane.xml");
		} catch (SAXException e) {
			System.err.print("������ SAX ������� " + e);
		} catch (IOException e) {
			System.err.print("������ I/� ������ " + e);
		}
	}
}