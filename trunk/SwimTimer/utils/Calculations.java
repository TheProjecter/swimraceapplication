package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

import entities.Event;
import entities.FinaBaseTimes;
import entities.Swimmer;

public class Calculations {

	private static Calculations instance;
	private Map<String, String> styleType = new Constants().getStyleNames();

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

	private Integer getMinuteFromString(String time) {
		String[] minSplit;
		minSplit = time.split(":");
		return (minSplit.length == 2) ? Integer.parseInt(minSplit[0]) : 0;
	}

	private Integer getSecondsFromString(String time) {
		String[] minSplit = time.split(":");
		String[] secSplit = ((minSplit.length == 2) ? minSplit[1] : minSplit[0])
				.split("\\.");
		return Integer.parseInt(secSplit[0]);
	}

	private Integer getMSecondsFromString(String time) {
		String[] minSplit = time.split(":");
		String[] secSplit = ((minSplit.length == 2) ? minSplit[1] : minSplit[0])
				.split("\\.");
		return Integer.parseInt(secSplit[1]);
	}

	public List<FinaBaseTimes> getAllBaseTimes() {
		List<FinaBaseTimes> fina = new ArrayList<FinaBaseTimes>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File(System.getProperty("user.dir")
					+ "/Data/utils/FINA Base Times.csv"));
			try {
				while (scanner.hasNextLine()) {
					String[] entry = scanner.nextLine().split(";");

					fina.add(new FinaBaseTimes(entry[0], entry[1], entry[2],
							Integer.parseInt(entry[3]), entry[4], entry[5],
							getMinuteFromString(entry[6]),
							getSecondsFromString(entry[6]),
							getMSecondsFromString(entry[6]), Double
									.parseDouble(entry[7].replace(",", "."))));
				}
			} finally {
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return fina;
	}

	public double getBaseTimeForEvent(Event event) {
		FinaBaseTimes finaBase = null;
		Boolean matchFoundPoolType = false;
		Boolean matchFoundStyle = false;
		Boolean matchFoundDistance = false;
		List<FinaBaseTimes> fina = getAllBaseTimes();
		for (FinaBaseTimes times : fina) {
			// establish the pooltype
			matchFoundPoolType = (times.getPoolType().equals("SCM")) ? (event
					.getPoolType().contains("25") ? true : false) : (times
					.getPoolType().equals("LCM")) ? (event.getPoolType()
					.contains("50") ? true : false) : false;
			// establish the style
			matchFoundStyle = (times.getStyle().equals("FREE")) ? (event
					.getStyle().equals(styleType.get("FREE").toString()) ? true
					: false)
					: (times.getStyle().equals("BACK")) ? (event.getStyle()
							.equals(styleType.get("BACK").toString()) ? true
							: false)
							: (times.getStyle().equals("BREAST")) ? (event
									.getStyle().equals(
											styleType.get("BREAST").toString()) ? true
									: false)
									: (times.getStyle().equals("FLY")) ? (event
											.getStyle().equals(
													styleType.get("FLY")
															.toString()) ? true
											: false) : (times.getStyle()
											.equals("MEDLEY")) ? (event
											.getStyle().equals(
													styleType.get("MEDLEY")
															.toString()) ? true
											: false) : false;
			// establish the length
			matchFoundDistance = (times.getLength().equals(event.getLength())) ? true
					: false;
			if (matchFoundPoolType && matchFoundStyle && matchFoundDistance) {
				return times.getBaseTimesSeconds();
			}
		}
		return 0;
	}

	public String calculateFinaPoints(double swimTime, double baseTime) {
		return String.format("%.5g%n", 1000 * Math.pow(baseTime / swimTime, 3));
	}
}
