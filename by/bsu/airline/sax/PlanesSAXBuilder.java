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
		// �������� SAX-�����������
		ph = new PlaneHandler();
		try {
			// �������� �������-�����������
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(ph);
		} catch (SAXException e) {
			System.err.print("������ SAX �������: " + e);
		}
	}

	public Set<Plane> getStudents() {
		return planes;
	}

	public void buildSetStudents(String fileName) {
		try {
			// ������ XML-���������
			reader.parse(fileName);
		} catch (SAXException e) {
			System.err.print("������ SAX �������: " + e);
		} catch (IOException e) {
			System.err.print("������ I/� ������: " + e);
		}
		planes = ph.getPlanes();
	}
}