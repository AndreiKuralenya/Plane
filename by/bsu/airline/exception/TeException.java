package by.bsu.airline.exception;

import java.util.Scanner;
import java.util.logging.Logger;
import by.bsu.airline.model.Airline;
import by.bsu.airline.model.Plane;

class TeException extends Exception {
	public static final Logger LOG = Logger.getAnonymousLogger();

	public void selectPlanesByFuelConsumption(Airline mairline) {

		while (true) {

			System.out
					.println("Range of consumption of fuel for the passenger and cargo plane: ");

			Scanner scanIn = new Scanner(System.in);
			String s = scanIn.nextLine();

			if (" ".equals(s))
				break;

			try {
				int fuel = Integer.parseInt(s);
				if (fuel <= 0) {
					throw new Exception();
				}
				for (Plane plane : mairline.getPlanes()) {
					if (fuel < plane.getFuelConsumtion()) {
						LOG.info("For you it is necessary- " + plane.getName()
								+ " with fuel consumption- "
								+ plane.getFuelConsumtion());
					} else
						LOG.info(plane.getName()
								+ "  the given plane does not approach you!");
				}
			} catch (Exception e) {
				LOG.warning("Incorrect input, try once again");

			}

		}
		LOG.info("Program end");
	}
}
