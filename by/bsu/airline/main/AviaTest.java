package by.bsu.airline.main;

import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Logger;

import by.bsu.airline.exceptions.NoFlightFoundException;
import by.bsu.airline.exceptions.WrongInputException;
import by.bsu.airline.model.Airline;
import by.bsu.airline.model.Plane;

public class AviaTest {
	public static final Logger LOG = Logger.getAnonymousLogger();

	public static void main(String[] args) {

		Plane mria = new Plane.Builder("AN Mria", 250 /* capacity */,
				300/* passenger */, 10000/* fuel */, 20000 /* range */).build();
		Plane airbus = new Plane.Builder("Airbus", 150 /* capacity */,
				600/* passenger */, 5000/* fuel */, 15000 /* range */).build();
		Plane boeing747 = new Plane.Builder("Boeing 747", 100 /* capacity */,
				120/* passenger */, 3000/* fuel */, 10000 /* range */).build();
		Plane boeing947 = new Plane.Builder("Boeing 947", 50 /* capacity */,
				60/* passenger */, 1000/* fuel */, 5000 /* range */).build();

		Airline mAirline = new Airline();
		mAirline.addPlane(mria);
		mAirline.addPlane(airbus);
		mAirline.addPlane(boeing747);
		mAirline.addPlane(boeing947);

		LOG.info("total Capacity of Airline: " + mAirline.totalCapacity());

		LOG.info("total Passenger of Airline: " + mAirline.totalPassenger());
		sortPlanesByRange(mAirline.getPlanes());
		selectPlanesByFuelConsumption(mAirline);
	}

	private static void sortPlanesByRange(java.util.List<Plane> planes) {

		final Logger LOG = Logger.getAnonymousLogger();
		LOG.info("Planes sorted by Range:");

		Collections.sort(planes);
		for (Plane plane : planes) {
			LOG.info("  " + plane.getName() + "; Range: " + plane.getRange());
		}

	}

	public static void selectPlanesByFuelConsumption(Airline airline) {

		final Logger LOG = Logger.getAnonymousLogger();
		LOG.info("Starting planes selection... ");

		while (true) {

			LOG.info("");
			LOG.info("Enter Fuel Consumption (press space and enter to exit): ");

			Scanner scanIn = new Scanner(System.in);
			String s = scanIn.nextLine();

			if (" ".equals(s))
				break;

			try {

				int fuel = 0;

				try {
					fuel = Integer.parseInt(s);
				} catch (Exception e) {
					throw new WrongInputException(e);
				}

				boolean found = false;

				for (Plane plane : airline.getPlanes()) {
					if (fuel < plane.getFuelConsumtion()) {
						found = true;
						LOG.info("Your match is " + plane.getName()
								+ "; fuel consumption: "
								+ plane.getFuelConsumtion());
					}
				}

				if (!found) {
					throw new NoFlightFoundException();
				}

			} catch (NoFlightFoundException e) {
				LOG.info("No planes found matching your criteria. Try again.");
			} catch (WrongInputException e) {
				LOG.warning("Incorrect input, try again");
			}
		}

		LOG.info("Plane selection finished.");
	}
}