package by.bsu.airline.dombuild;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.bsu.airline.model.Plane;


public class PlanesDOMBuilder {
	private Set<Plane> planes;
	private DocumentBuilder docBuilder;

	public PlanesDOMBuilder() {
		this.planes = new HashSet<Plane>();
		// �������� DOM-�����������
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err.println("������ ������������ �������: " + e);
		}
	}

	public Set<Plane> getPlanes() {
		return planes;
	}

	public void buildSetPlanes(String fileName) {
		Document doc = null;
		try {
			// parsing XML-��������� � �������� ����������� ���������
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			// ��������� ������ �������� ��������� <plane>
			NodeList planesList = root.getElementsByTagName("plane");
			for (int i = 0; i < planesList.getLength(); i++) {
				Element planeElement = (Element) planesList.item(i);
				Plane plane = buildPlane(planeElement);
				planes.add(plane);
			}
		} catch (IOException e) {
			System.err.println("File error or I/O error: " + e);
		} catch (SAXException e) {
			System.err.println("Parsing failure: " + e);
		}
	}

	private Plane buildPlane(Element planeElement) {
		Plane plane = new Plane(null);
		// ���������� ������� plane
		plane.setView(planeElement.getAttribute("view")); // ��������
															// �� null
		plane.setName(getElementTextContent(planeElement, "name"));
		Integer year = Integer.parseInt(getElementTextContent(planeElement,
				"year"));
		plane.setYear(year);
		plane.Characteristics characteristics = plane.getCharacteristics();
		// ���������� ������� characteristics
		Element characteristicsElement = (Element) planeElement
				.getElementsByTagName("characteristics").item(0);
		characteristics.setCapacity(getElementTextContent(
				characteristicsElement, "capacity"));
		characteristics.setPassanger(getElementTextContent(
				characteristicsElement, "passanger"));
		characteristics.setFuelWeight(getElementTextContent(
				characteristicsElement, "fuel-weight"));
		characteristics.setCruisingSpeed(getElementTextContent(
				characteristicsElement, "cruising-speed"));
		characteristics.setRange(getElementTextContent(characteristicsElement,
				"range"));
		characteristics.setID(planeElement.getAttribute("ID"));
		return plane;
	}

	// ��������� ���������� ����������� ����
	private static String getElementTextContent(Element element,
			String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = (Node) nList.item(0);
		String text = node.getTextContent();
		return text;
	}
}