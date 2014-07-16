package by.bsu.airline.model;

public class Plane implements Comparable<Plane> {
	public String name;
	public int capacity;
	public int passenger;
	public int fuelConsumtion;
	public int range;

	public static class Builder {
		public String name;
		public int capacity;
		public int passenger;
		public int fuelConsumtion;
		public int range;

		public Builder(String name, int capacity, int passenger,
				int fuelConsumtion, int range) {
			this.name = name;
			this.capacity = capacity;
			this.passenger = passenger;
			this.fuelConsumtion = fuelConsumtion;
			this.range = range;
		}

		public Plane build() {
			return new Plane(this);

		}
	}

	public Plane(Builder builder) {
		name = builder.name;
		capacity = builder.capacity;
		passenger = builder.passenger;
		fuelConsumtion = builder.fuelConsumtion;
		range = builder.range;
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getPassenger() {
		return passenger;
	}

	public int getFuelConsumtion() {
		return fuelConsumtion;
	}

	public int getRange() {
		return range;
	}

	public boolean equals(Plane plane) {
		if (!(plane instanceof Plane))
			return false;
		Plane tmp = (Plane) plane;
		return name.equals(tmp.name) && range == tmp.range
				&& capacity == tmp.capacity && passenger == tmp.passenger
				&& fuelConsumtion == tmp.fuelConsumtion;
	}

	public int hashCode(Plane plane) {
		Object object = new Object();
		int hCode;
		hCode = object.hashCode();
		return hCode;
	}

	public int compareTo(Plane p) {
		return Integer.signum(this.getRange() - p.getRange());
	}
}
