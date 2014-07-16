package by.bsu.airline.stax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.bsu.airline.model.Plane;
import by.bsu.airline.sax.PlaneEnum;

public class PlanesStaxBuilder {
	private HashSet<Plane> planes = new HashSet<>();
	private XMLInputFactory inputFactory;

	public PlanesStaxBuilder() {
		inputFactory = XMLInputFactory.newInstance();
	}

	public Set<Plane> getStudents() {
		return planes;
	}

	public void buildSetPlanes(String fileName) {
		FileInputStream inputStream = null;
		XMLStreamReader reader = null;
		String name;
		try {
			inputStream = new FileInputStream(new File(fileName));
			reader = inputFactory.createXMLStreamReader(inputStream);
			// StAX parsing
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if (PlaneEnum.valueOf(name.toUpperCase()) == PlaneEnum.PLANES) {
						Plane pl = buildPlane(reader);
						planes.add(pl);
					}
				}
			}
		} catch (XMLStreamException ex) {
			System.err.println("StAX parsing error! " + ex.getMessage());
		} catch (FileNotFoundException ex) {
			System.err.println("File " + fileName + " not found! " + ex);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {

				System.err.println("Impossible close file " + fileName + " : "
						+ e);
			}
		}
	}

	private Plane buildPlane(XMLStreamReader reader) throws XMLStreamException {
		Plane pl = new Plane();
		pl.setID(reader.getAttributeValue(null, PlaneEnum.ID.getValue()));
		pl.setView(reader.getAttributeValue(null, PlaneEnum.VIEW.getValue())); // проверить
																				// на
																				// null
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (PlaneEnum.valueOf(name.toUpperCase())) {
				case NAME:
					pl.setName(getXMLText(reader));
					break;
				case YEAR:
					name = getXMLText(reader);
					pl.setYear(Integer.parseInt(name));
					break;
				case COUNTRY:
					pl.setCountry(getXMLText(reader));
					break;
				case VIEW:
					pl.setView(getXMLText(reader));
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (PlaneEnum.valueOf(name.toUpperCase()) == PlaneEnum.PLANES) {
					return pl;
				}
				break;
			}

		}
		throw new XMLStreamException("Unknown element in tag Plane");
	}

	private Plane.Characteristics getXMLCharacteristics(XMLStreamReader reader)
			throws XMLStreamException {
		Plane.Characteristics ch = new Plane.Characteristics();
		int type;
		String name;
		while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (PlaneEnum.valueOf(name.toUpperCase())) {
				case CAPASITY:
					ch.getCapasity(Integer.parseInt(name));
					break;
				case PASSANGER:
					ch.getPassanger(Integer.parseInt(name));
					break;
				case FUELWEIGHT:
					ch.getFuelweight(Integer.parseInt(name));
					break;
				case CRUISINGSPEED:
					ch.getCruisingspeed(Integer.parseInt(name));
					break;
				case RANGE:
					ch.getRange(Integer.parseInt(name));
					break;
				case PRACTICALCEILING:
					ch.getPracticalceiling(Integer.parseInt(name));
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (PlaneEnum.valueOf(name.toUpperCase()) == PlaneEnum.CHARACTERISTICS) {
					return ch;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag Characteristics");
	}

	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}