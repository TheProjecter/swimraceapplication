package work;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

import utils.Constants;

import entities.Event;
import entities.Heat;
import entities.Lane;
import entities.LaneComparator;
import entities.Registration;
import entities.Swimmer;

public class Operations {

	private Map<String, String> dataFile = new Constants().getDataFiles();

	public Operations() {
	}

	public void clearDB(String dbType) {
		try {
			FileWriter fstream = new FileWriter(dataFile.get(dbType).toString());
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("");
			out.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public List<Swimmer> returnAllSwimmers() {
		Scanner scanner;
		List<Swimmer> swimmers = new ArrayList<Swimmer>();
		try {
			scanner = new Scanner(new File(dataFile.get("swimmers")));
			try {
				while (scanner.hasNextLine()) {
					String[] entry = scanner.nextLine().split(";");
					swimmers.add(new Swimmer(entry[0], entry[1], entry[2],
							entry[3], entry[4]));
				}
			} finally {
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return swimmers;
	}

	public Swimmer returnSwimmer(String swimmerName) {
		Scanner scanner;
		Swimmer swimmer = new Swimmer(null, null, null, null, null);
		try {
			scanner = new Scanner(new File(dataFile.get("swimmers")));
			try {
				while (scanner.hasNextLine()) {
					String[] entry = scanner.nextLine().split(";");
					if (entry[0].toLowerCase()
							.equals(swimmerName.toLowerCase())) {
						swimmer.setName(entry[0]);
						swimmer.setBirthYear(entry[1]);
						swimmer.setAgeGroup(entry[2]);
						swimmer.setGender(entry[3]);
						swimmer.setClub(entry[4]);
						break;
					}
				}
			} finally {
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return swimmer;
	}

	public List<Swimmer> deleteSwimmers(List<Swimmer> swimmers, Swimmer swimmer) {
		List<Swimmer> newSwimmersList = new ArrayList<Swimmer>();
		for (Swimmer sw : swimmers) {
			if (!sw.getName().equals(swimmer.getName())) {
				newSwimmersList.add(sw);
			}
		}
		return newSwimmersList;
	}

	public List<Event> returnAllEvents() {
		Scanner scanner;
		List<Event> events = new ArrayList<Event>();
		String[] entry = new String[5];
		try {
			scanner = new Scanner(new File(dataFile.get("events")));
			try {
				while (scanner.hasNextLine()) {
					entry = scanner.nextLine().split(";");
					Event event = new Event(entry[0], entry[1], entry[2],
							entry[3], entry[4]);
					events.add(event);
				}
			} finally {
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return events;
	}

	public Event returnEvent(String eventName) {
		Scanner scanner;
		Event event = new Event(null, null, null, null, null);
		try {
			scanner = new Scanner(new File(dataFile.get("events")));
			try {
				while (scanner.hasNextLine()) {
					String[] entry = scanner.nextLine().split(";");
					if (entry[0].toLowerCase().equals(eventName.toLowerCase())) {
						event.setName(entry[0]);
						event.setLength(entry[1]);
						event.setStyle(entry[2]);
						event.setGender(entry[3]);
						event.setPoolType(entry[4]);
						break;
					}
				}
			} finally {
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return event;
	}

	public List<Event> deleteEvents(List<Event> events, Event event) {
		List<Event> newEventsList = new ArrayList<Event>();
		for (Event ev : events) {
			if (!ev.getName().equals(event.getName())) {
				newEventsList.add(ev);
			}
		}

		return newEventsList;
	}

	public List<Registration> getAllRegistrations() {
		Scanner scanner;
		List<Registration> registrationsList = new ArrayList<Registration>();
		try {
			scanner = new Scanner(new File(dataFile.get("registrations")));
			try {
				while (scanner.hasNextLine()) {
					String[] entry = scanner.nextLine().split(";");
					Registration registration = new Registration(
							returnSwimmer(entry[0].toString()),
							returnEvent(entry[5]), Integer.parseInt(entry[6]),
							Integer.parseInt(entry[7]), Integer
									.parseInt(entry[8]));
					registrationsList.add(registration);
				}
			} finally {
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return registrationsList;
	}

	public Registration getRegistration(String swimmerName, String eventName,
			Integer minutes, Integer seconds, Integer mSeconds) {
		Scanner scanner;
		List<Registration> registrationsList = new ArrayList<Registration>();
		try {
			scanner = new Scanner(new File(dataFile.get("registrations")));
			try {
				while (scanner.hasNextLine()) {
					String[] entry = scanner.nextLine().split(";");
					if (entry[0].toString().equals(swimmerName)
							&& entry[5].equals(eventName)
							&& Integer.parseInt(entry[6]) == minutes
							&& Integer.parseInt(entry[7]) == seconds
							&& Integer.parseInt(entry[8]) == mSeconds) {
						Registration registration = new Registration(
								returnSwimmer(entry[0].toString()),
								returnEvent(entry[5]), Integer
										.parseInt(entry[6]), Integer
										.parseInt(entry[7]), Integer
										.parseInt(entry[8]));
						return registration;
					}
				}
			} finally {
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Registration> getRegistrationsForEvent(Event event) {
		Scanner scanner;
		List<Registration> registrationsList = new ArrayList<Registration>();
		try {
			scanner = new Scanner(new File(dataFile.get("registrations")));
			try {
				while (scanner.hasNextLine()) {
					String[] entry = scanner.nextLine().split(";");
					if (entry[5].toLowerCase().equals(
							event.getName().toLowerCase())) {
						Registration registration = new Registration(
								returnSwimmer(entry[0].toString()),
								returnEvent(entry[5]), Integer
										.parseInt(entry[6]), Integer
										.parseInt(entry[7]), Integer
										.parseInt(entry[8]));
						registrationsList.add(registration);
					}
				}
			} finally {
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return registrationsList;
	}

	public List<Registration> getRegistrationsForSwimmer(Swimmer swimmer) {
		Scanner scanner;
		List<Registration> registrationsList = new ArrayList<Registration>();
		try {
			scanner = new Scanner(new File(dataFile.get("registrations")));
			try {
				while (scanner.hasNextLine()) {
					String[] entry = scanner.nextLine().split(";");
					if (entry[0].toLowerCase().equals(
							swimmer.getName().toLowerCase())) {
						Registration registration = new Registration(
								returnSwimmer(entry[0].toString()),
								returnEvent(entry[5]), Integer
										.parseInt(entry[6]), Integer
										.parseInt(entry[7]), Integer
										.parseInt(entry[8]));
						registrationsList.add(registration);
					}
				}
			} finally {
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return registrationsList;
	}
	
	public List<Registration> deleteRegistration(
			List<Registration> registrations, Registration registration) {
		List<Registration> newRegistrationList = new ArrayList<Registration>();
		for (Registration reg : registrations) {
			if (!(reg.getSwimmer().getName().equals(
					registration.getSwimmer().getName())
					&& reg.getEvent().getName().equals(
							registration.getEvent().getName())
					&& (reg.getMinutes() == registration.getMinutes())
					&& (reg.getSeconds() == registration.getSeconds()) && (reg
					.getmSeconds() == registration.getmSeconds()))) {
				newRegistrationList.add(reg);
			}
		}

		return newRegistrationList;
	}

	public void deleteRegistrationForSwimmer(Swimmer swimmer) {
		List<Registration> registrations = getRegistrationsForSwimmer(swimmer);
		for (Registration reg : registrations) {
			String lineToRemove = reg.getSwimmer().getName() + ";"
			+ reg.getSwimmer().getBirthYear() + ";"
			+ reg.getSwimmer().getAgeGroup() + ";"
			+ reg.getSwimmer().getGender() + ";"
			+ reg.getSwimmer().getClub() + ";"
			+ reg.getEvent().getName() + ";"
			+ setZero(reg.getMinutes().toString()) + ";"
			+ setZero(reg.getSeconds().toString()) + ";"
			+ setZero(reg.getmSeconds().toString());
			removeLineFromFile(dataFile.get("registrations"), lineToRemove);
		}
	}

	public void deleteRegistrationForEvent(Event event) {
		List<Registration> registrations = getRegistrationsForEvent(event);
		for (Registration reg : registrations) {
			String lineToRemove = reg.getSwimmer().getName() + ";"
			+ reg.getSwimmer().getBirthYear() + ";"
			+ reg.getSwimmer().getAgeGroup() + ";"
			+ reg.getSwimmer().getGender() + ";"
			+ reg.getSwimmer().getClub() + ";"
			+ reg.getEvent().getName() + ";"
			+ setZero(reg.getMinutes().toString()) + ";"
			+ setZero(reg.getSeconds().toString()) + ";"
			+ setZero(reg.getmSeconds().toString());
			removeLineFromFile(dataFile.get("registrations"), lineToRemove);
		}
	}
	
	public List<Lane> createLanes(Event event) {
		List<Lane> laneList = new ArrayList<Lane>();
		List<Registration> registrationsList = getRegistrationsForEvent(event);

		for (Registration reg : registrationsList) {
			Lane lane = new Lane(reg.getSwimmer(), reg.getEvent(), reg
					.getEntryTime(), reg.getMinutes(), reg.getSeconds(), reg
					.getmSeconds());
			laneList.add(lane);
		}
		return laneList;
	}

	public Lane evaluateLane(List<Lane> laneList, int position) {
		try {
			return laneList.get(position);
		} catch (IndexOutOfBoundsException e) {
			return new Lane();
		}
	}

	public List<Lane> setLanesInOrder(List<Lane> laneList) {
		List<Lane> arrangedList = new ArrayList<Lane>();

		arrangedList.add(evaluateLane(laneList, 6));
		arrangedList.add(evaluateLane(laneList, 4));
		arrangedList.add(evaluateLane(laneList, 2));
		arrangedList.add(evaluateLane(laneList, 0));
		arrangedList.add(evaluateLane(laneList, 1));
		arrangedList.add(evaluateLane(laneList, 3));
		arrangedList.add(evaluateLane(laneList, 5));
		arrangedList.add(evaluateLane(laneList, 7));

		return arrangedList;

	}

	public List<Heat> generateHeats(Event event) {
		List<Lane> laneList = new ArrayList<Lane>();
		List<Lane> lanesOnHeat = new ArrayList<Lane>();
		List<Heat> heatList = new ArrayList<Heat>();

		// create and sort lanes
		laneList = createLanes(event);
		Collections.sort(laneList, new LaneComparator());

		int heatCount = (laneList.size()) / 8 + 1;
		int startPos = 0;
		int endPos = (8 > laneList.size()) ? laneList.size() : 8;
		for (int i = heatCount; i > 0; i--) {
			lanesOnHeat = laneList.subList(startPos, endPos);
			lanesOnHeat = setLanesInOrder(lanesOnHeat);
			heatList.add(new Heat(event.getName(), i, lanesOnHeat));
			startPos = startPos + 8;
			endPos = ((endPos + 8) < laneList.size()) ? (endPos + 8) : laneList
					.size();
		}

		return heatList;
	}

	public void registerHeats(List<Heat> heatList, String fileName) {
		Collections.sort(heatList);
		try {
//			FileWriter fstream = new FileWriter(dataFile.get("heats")
//					.toString(), true);
			FileWriter fstream = new FileWriter(fileName, true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.newLine();
			out.write("Proba " + heatList.get(0).getEventName());
			out.newLine();
			out.newLine();
			for (Heat heats : heatList) {
				out.write("Seria " + heats.getHeatNumber());
				out.newLine();
				if (heats.getLane1().equals(null)) {
					out.write("Culoar " + ";"
							+ heats.getLane1().getLaneNumber());
					out.newLine();
				} else {
					out.write("Culoar "
							+ ";"
							+ heats.getLane1().getLaneNumber()
							+ " ; "
							+ heats.getLane1().getSwimmer().getName()
							+ " ; "
							+ heats.getLane1().getSwimmer().getClub()
							+ " ; "
							+ heats.getLane1().getSwimmer().getAgeGroup()
							+ " ; "
							+ padLeft(Integer.toString(heats.getLane1()
									.getEntryMinutes()), 2)
							+ "."
							+ padLeft(Integer.toString(heats.getLane1()
									.getEntrySecondes()), 2)
							+ ":"
							+ padLeft(Integer.toString(heats.getLane1()
									.getEntryMSeconds()), 2));
					out.newLine();
				}
				out.write("Culoar "
						+ ";"
						+ heats.getLane2().getLaneNumber()
						+ " ; "
						+ heats.getLane2().getSwimmer().getName()
						+ " ; "
						+ heats.getLane2().getSwimmer().getClub()
						+ " ; "
						+ heats.getLane2().getSwimmer().getAgeGroup()
						+ " ; "
						+ padLeft(Integer.toString(heats.getLane2()
								.getEntryMinutes()), 2)
						+ "."
						+ padLeft(Integer.toString(heats.getLane2()
								.getEntrySecondes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane2()
								.getEntryMSeconds()), 2));
				out.newLine();
				out.write("Culoar "
						+ ";"
						+ heats.getLane3().getLaneNumber()
						+ " ; "
						+ heats.getLane3().getSwimmer().getName()
						+ " ; "
						+ heats.getLane3().getSwimmer().getClub()
						+ " ; "
						+ heats.getLane3().getSwimmer().getAgeGroup()
						+ " ; "
						+ padLeft(Integer.toString(heats.getLane3()
								.getEntryMinutes()), 2)
						+ "."
						+ padLeft(Integer.toString(heats.getLane3()
								.getEntrySecondes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane3()
								.getEntryMSeconds()), 2));
				out.newLine();
				out.write("Culoar "
						+ ";"
						+ heats.getLane4().getLaneNumber()
						+ " ; "
						+ heats.getLane4().getSwimmer().getName()
						+ " ; "
						+ heats.getLane4().getSwimmer().getClub()
						+ " ; "
						+ heats.getLane4().getSwimmer().getAgeGroup()
						+ " ; "
						+ padLeft(Integer.toString(heats.getLane4()
								.getEntryMinutes()), 2)
						+ "."
						+ padLeft(Integer.toString(heats.getLane4()
								.getEntrySecondes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane4()
								.getEntryMSeconds()), 2));
				out.newLine();
				out.write("Culoar "
						+ ";"
						+ heats.getLane5().getLaneNumber()
						+ " ; "
						+ heats.getLane5().getSwimmer().getName()
						+ " ; "
						+ heats.getLane5().getSwimmer().getClub()
						+ " ; "
						+ heats.getLane5().getSwimmer().getAgeGroup()
						+ " ; "
						+ padLeft(Integer.toString(heats.getLane5()
								.getEntryMinutes()), 2)
						+ "."
						+ padLeft(Integer.toString(heats.getLane5()
								.getEntrySecondes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane5()
								.getEntryMSeconds()), 2));
				out.newLine();
				out.write("Culoar "
						+ ";"
						+ heats.getLane6().getLaneNumber()
						+ " ; "
						+ heats.getLane6().getSwimmer().getName()
						+ " ; "
						+ heats.getLane6().getSwimmer().getClub()
						+ " ; "
						+ heats.getLane6().getSwimmer().getAgeGroup()
						+ " ; "
						+ padLeft(Integer.toString(heats.getLane6()
								.getEntryMinutes()), 2)
						+ "."
						+ padLeft(Integer.toString(heats.getLane6()
								.getEntrySecondes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane6()
								.getEntryMSeconds()), 2));
				out.newLine();
				out.write("Culoar "
						+ ";"
						+ heats.getLane7().getLaneNumber()
						+ " ; "
						+ heats.getLane7().getSwimmer().getName()
						+ " ; "
						+ heats.getLane7().getSwimmer().getClub()
						+ " ; "
						+ heats.getLane7().getSwimmer().getAgeGroup()
						+ " ; "
						+ padLeft(Integer.toString(heats.getLane7()
								.getEntryMinutes()), 2)
						+ "."
						+ padLeft(Integer.toString(heats.getLane7()
								.getEntrySecondes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane7()
								.getEntryMSeconds()), 2));
				out.newLine();
				out.write("Culoar "
						+ ";"
						+ heats.getLane8().getLaneNumber()
						+ " ; "
						+ heats.getLane8().getSwimmer().getName()
						+ " ; "
						+ heats.getLane8().getSwimmer().getClub()
						+ " ; "
						+ heats.getLane8().getSwimmer().getAgeGroup()
						+ " ; "
						+ padLeft(Integer.toString(heats.getLane8()
								.getEntryMinutes()), 2)
						+ "."
						+ padLeft(Integer.toString(heats.getLane8()
								.getEntrySecondes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane8()
								.getEntryMSeconds()), 2));
				out.newLine();
				out.newLine();
			}
			out.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void removeLineFromFile(String file, String lineToRemove) {

		try {

			File inFile = new File(file);

			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}

			// Construct the new file that will later be renamed to the original
			// filename.
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			String line = null;

			// Read from the original file and write to the new
			// unless content matches data to be removed.
			while ((line = br.readLine()) != null) {
				if (!line.trim().equals(lineToRemove)) {
					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			br.close();

			// Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			// Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static String padLeft(String s, int n) {
		return String.format("%1$#" + n + "s", s).replace(' ', '0');
	}

	public String setZero(String value) {
		return (value.length() < 2) ? "0" + value : value;
	}

}
