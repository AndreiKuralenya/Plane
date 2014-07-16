package by.bsu.airline.sax;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import by.bsu.airline.model.Plane;

public class PlaneHandler extends DefaultHandler {
	private Set<Plane> planes;
	private Plane current;
	private PlaneEnum currentEnum;
	private EnumSet<PlaneEnum> withText;

	public PlaneHandler() {
		planes = new HashSet<Plane>();
		withText = EnumSet.range(PlaneEnum.NAME, PlaneEnum.COUNTRY);
	}

	public Set<Plane> getPlanes() {
		return planes;
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attrs) {
		if ("plane".equals(localName)) {
			current = new Plane(null);
			current.setID(attrs.getValue(0));
			if (attrs.getLength() == 2) {
				current.setView(attrs.getValue(1));
			}
		} else {
			PlaneEnum temp = PlaneEnum.valueOf(localName.toUpperCase());
			if (withText.contains(temp)) {
				currentEnum = temp;
			}
		}
	}

	public void endElement(String uri, String localName, String qName) {
		if ("plane".equals(localName)) {
			planes.add(current);
		}
	}

	public void characters(char[] ch, int start, int length) {
		String s = new String(ch, start, length).trim();
		if (currentEnum != null) {
			switch (currentEnum) {
			case NAME:
				current.setName(s);
				break;
			case AIRCOMPANY:
				current.setAircompany(s);
				break;
			case YEAR:
				current.getYear().setYear(Integer.parseInt(s));
				break;
			case VIEW:
				current.getView().setView(s);
				break;
			case COUNTRY:
				current.getCountry().setCountry(s);
				break;
			case CAPASITY:
				current.getCapasity(s).setCapasity(s);
				break;
			case PASSANGER:
				current.getPassanger(s).setPassanger(s);
				break;
			case FUELWEIGHT:
				current.getFuelweight().setFuelweight(s);
				break;
			case CRUISINGSPEED:
				current.getCruisingspeed().setCruisingspeed(s);
				break;
			case RANGE:
				current.getRange(s).setRange(s);
				break;
			case PRACTICALCEILING:
				current.getPracticalceiling(s).setPracticalceiling(s);
				break;
			case LENGHT:
				current.getLenght().setLenght(s);
				break;
			case HEIGHT:
				current.getHeight().setHeight(s);
				break;
			case WIDTH:
				current.getWidth().setWidth(s);
				break;
			default:
				throw new EnumConstantNotPresentException(
						currentEnum.getDeclaringClass(), currentEnum.name());
			}
		}
		currentEnum = null;
	}
}
