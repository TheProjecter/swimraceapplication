package utils;

public class Calculations {

	private static Calculations instance;
	private Calculations() {
	}

	public static synchronized Calculations getInstance() {
		if (instance == null)
			instance = new Calculations();
		return instance;
	}

	public int calculateLocalPoints(int place) {
		return (place == 1) ? 10 : (place == 2) ? 8 : (place == 3) ? 6
				: (place == 4) ? 5 : (place == 5) ? 4 : (place == 6) ? 3
						: (place == 7) ? 2 : (place == 8) ? 1 : 0;
	}
}
