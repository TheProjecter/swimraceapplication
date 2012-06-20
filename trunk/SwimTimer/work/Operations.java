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

import utils.AgeGroup;
import utils.Constants;

import entities.Event;
import entities.Heat;
import entities.HeatComparator;
import entities.Lane;
import entities.LaneComparator;
import entities.Registration;
import entities.Result;
import entities.Swimmer;

public class Operations {

	private Map<String, String> dataFile = new Constants().getDataFiles();
	private Map<String, String> pathFile = new Constants().getDataFiles();
	private String osName = new Constants().getOsName();
	private String separator = null;
	
	public Operations() {
		// setting the separator Linux/Windows
		if (osName.toLowerCase().startsWith("linux")) {
			setSeparator("/");
		} else if (osName.toLowerCase().startsWith("windows")) {
			setSeparator("\\");
		}
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
			handleFile("core", "swimmers");
			scanner = new Scanner(new File(pathFile.get("core") + separator
					+ dataFile.get("swimmers")));
			try {
				while (scanner.hasNextLine()) {
					String[] entry = scanner.nextLine().split(";");
					swimmers.add(new Swimmer(entry[0], entry[1], entry[2],
							entry[3], entry[4]));
				}
			} finally {
				scanner.close();
			}
		} catch (IOException e) {
		}

		return swimmers;
	}

	public Swimmer returnSwimmer(String swimmerName) {
		Scanner scanner;
		Swimmer swimmer = new Swimmer(null, null, null, null, null);
		try {
			scanner = new Scanner(new File(pathFile.get("core") + separator
					+ dataFile.get("swimmers")));
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
	
	public boolean existsSwimmer(String swimmerName) {
		Swimmer sw = returnSwimmer(swimmerName);
		if (sw.getName() == null) {
			return false;
		}
		return true;
	}

	public List<Event> returnAllEvents() {
		Scanner scanner;
		List<Event> events = new ArrayList<Event>();
		String[] entry = new String[5];
		try {
			handleFile("core", "events");
			scanner = new Scanner(new File(pathFile.get("core") + separator
					+ dataFile.get("events")));
			try {
				while (scanner.hasNextLine()) {
					entry = scanner.nextLine().split(";");
					Event event = new Event(entry[0], entry[1], entry[2],
							entry[3]);
					events.add(event);
				}
			} finally {
				scanner.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return events;
	}

	public Event returnEvent(String eventName) {
		Scanner scanner;
		Event event = new Event(null, null, null, null);
		try {
			scanner = new Scanner(new File(pathFile.get("core") + separator
					+ dataFile.get("events")));
			try {
				while (scanner.hasNextLine()) {
					String[] entry = scanner.nextLine().split(";");
					if (entry[0].toLowerCase().equals(eventName.toLowerCase())) {
						event.setName(entry[0]);
						event.setLength(entry[1]);
						event.setStyle(entry[2]);
						event.setPoolType(entry[3]);
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

	public boolean existsEvent(String eventName) {
		List<Event> events = returnAllEvents();
		for (Event ev : events) {
			String[] eventInternalName = ev.getName().split("- ");
			if (eventInternalName[1].equals(eventName)) {
				return true;
			}
		}
		return false;
	}

	public void registerRegistration(Registration registration)
			throws IOException {
		handleFile("core", "registrations");
		FileWriter fstream = new FileWriter(pathFile.get("core") + separator
				+ dataFile.get("registrations"), true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(registration.getSwimmer().getName() + ";"
				+ registration.getSwimmer().getBirthYear() + ";"
				+ registration.getSwimmer().getAgeGroup() + ";"
				+ registration.getSwimmer().getGender() + ";"
				+ registration.getSwimmer().getClub() + ";"
				+ registration.getEvent().getName() + ";"
				+ padLeft(Integer.toString(registration.getMinutes()), 2) + ";"
				+ padLeft(Integer.toString(registration.getSeconds()), 2) + ";"
				+ padLeft(Integer.toString(registration.getmSeconds()), 2));
		out.newLine();
		out.close();
	}

	public List<Registration> getAllRegistrations() {
		Scanner scanner;
		List<Registration> registrationsList = new ArrayList<Registration>();
		try {
			scanner = new Scanner(new File(pathFile.get("core") + separator
					+ dataFile.get("registrations")));
			try {
				while (scanner.hasNextLine()) {
					String[] entry = scanner.nextLine().split(";");
					//System.out.println(returnEvent(entry[5]).getName());
					Registration registration = new Registration(
							returnSwimmer(entry[0].toString()),
							returnEvent(entry[5]), 
							Integer.parseInt(entry[6]),
							Integer.parseInt(entry[7]),
							Integer.parseInt(entry[8]));
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
		try {
			scanner = new Scanner(new File(pathFile.get("core") + separator
					+ dataFile.get("registrations")));
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
								returnEvent(entry[5]),
								Integer.parseInt(entry[6]),
								Integer.parseInt(entry[7]),
								Integer.parseInt(entry[8]));
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

	public boolean isSwimmerRegisteredForEvent(Swimmer swimmer, Event event) {
		List<Registration> registrations = getRegistrationsForSwimmer(swimmer);
		for (Registration reg : registrations) {
			if (reg.getEvent().getName().equals(event.getName())) {
				return true;
			}
		}
		return false;
	}

	public List<Registration> getRegistrationsForEvent(Event event,
			String heatGender) {
		Scanner scanner;
		List<Registration> registrationsList = new ArrayList<Registration>();
		try {
			scanner = new Scanner(new File(pathFile.get("core") + separator
					+ dataFile.get("registrations")));
			try {
				while (scanner.hasNextLine()) {
					String[] entry = scanner.nextLine().split(";");
					if (entry[5].toLowerCase().equals(
							event.getName().toLowerCase())) {
						Registration registration = new Registration(
								returnSwimmer(entry[0].toString()),
								returnEvent(entry[5]),
								Integer.parseInt(entry[6]),
								Integer.parseInt(entry[7]),
								Integer.parseInt(entry[8]));
						// add it to the return list only if the gender of the
						// Heat corresponds to the swimmers gender
						boolean addRegistration = registration.getSwimmer()
								.getGender().toString().equals(heatGender) ? true
								: heatGender.equals("Mixt") ? true : false;
						if (addRegistration) {
							registrationsList.add(registration);
						}
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
			handleFile("core", "registrations");
			scanner = new Scanner(new File(pathFile.get("core") + separator
					+ dataFile.get("registrations")));
			try {
				while (scanner.hasNextLine()) {
					String[] entry = scanner.nextLine().split(";");
					if (entry[0].toLowerCase().equals(
							swimmer.getName().toLowerCase())) {
						Registration registration = new Registration(
								returnSwimmer(entry[0].toString()),
								returnEvent(entry[5]),
								Integer.parseInt(entry[6]),
								Integer.parseInt(entry[7]),
								Integer.parseInt(entry[8]));
						registrationsList.add(registration);
					}
				}
			} finally {
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return registrationsList;
	}

	public List<Registration> deleteRegistration(
			List<Registration> registrations, Registration registration) {
		List<Registration> newRegistrationList = new ArrayList<Registration>();
		for (Registration reg : registrations) {
			if (!(reg.getSwimmer().getName()
					.equals(registration.getSwimmer().getName())
					&& reg.getEvent().getName()
							.equals(registration.getEvent().getName())
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
			removeLineFromFile(
					pathFile.get("core") + separator + dataFile.get("registrations"),
					lineToRemove);
		}
	}

	public void deleteRegistrationForEvent(Event event, String heatGender) {
		List<Registration> registrations = getRegistrationsForEvent(event,
				heatGender);
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
			removeLineFromFile(
					pathFile.get("core") + separator + dataFile.get("registrations"),
					lineToRemove);
		}
	}

	public List<Lane> createLanes(Event event, String heatGender) {
		List<Lane> laneList = new ArrayList<Lane>();
		List<Registration> registrationsList = getRegistrationsForEvent(event,
				heatGender);

		for (Registration reg : registrationsList) {
			Lane lane = new Lane(reg.getSwimmer(), reg.getEvent(),
					reg.getEntryTime(), reg.getMinutes(), reg.getSeconds(),
					reg.getmSeconds());
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

	public List<Lane> setLanesInOrder(List<Lane> laneList, int laneNumbers) {
		List<Lane> arrangedList = new ArrayList<Lane>();

		if (laneNumbers == 8) {
			arrangedList.add(evaluateLane(laneList, 6));
			arrangedList.add(evaluateLane(laneList, 4));
			arrangedList.add(evaluateLane(laneList, 2));
			arrangedList.add(evaluateLane(laneList, 0));
			arrangedList.add(evaluateLane(laneList, 1));
			arrangedList.add(evaluateLane(laneList, 3));
			arrangedList.add(evaluateLane(laneList, 5));
			arrangedList.add(evaluateLane(laneList, 7));
		} else if (laneNumbers == 6) {
			arrangedList.add(evaluateLane(laneList, 4));
			arrangedList.add(evaluateLane(laneList, 2));
			arrangedList.add(evaluateLane(laneList, 0));
			arrangedList.add(evaluateLane(laneList, 1));
			arrangedList.add(evaluateLane(laneList, 3));
			arrangedList.add(evaluateLane(laneList, 5));
		}
		return arrangedList;

	}

	public List<Heat> generateHeats(Event event, String poolType,
			int swimmersPerHeat, String heatGender) {
		List<Lane> laneList = new ArrayList<Lane>();
		List<Lane> lanesOnHeat = new ArrayList<Lane>();
		List<Heat> heatList = new ArrayList<Heat>();
		int laneNumbers = (poolType.contains("25") ? 6 : 8);

		// create and sort lanes
		laneList = createLanes(event, heatGender);
		Collections.sort(laneList, new LaneComparator());

		int heatCount = laneList.size() % swimmersPerHeat == 0 ? laneList
				.size() / swimmersPerHeat : laneList.size() / swimmersPerHeat
				+ 1;
		int startPos = 0;
		int endPos = (swimmersPerHeat > laneList.size()) ? laneList.size()
				: swimmersPerHeat;
		for (int i = heatCount; i > 0; i--) {
			lanesOnHeat = laneList.subList(startPos, endPos);
			lanesOnHeat = setLanesInOrder(lanesOnHeat, laneNumbers);
			heatList.add(new Heat(event.getName(), i, lanesOnHeat));
			startPos = startPos + swimmersPerHeat;
			endPos = ((endPos + swimmersPerHeat) < laneList.size()) ? (endPos + swimmersPerHeat)
					: laneList.size();
		}

		Collections.sort(heatList, new HeatComparator());
		return heatList;
	}

	public void registerHeats(String poolType, List<Heat> heatList,
			String fileName) {
		Collections.sort(heatList);
		try {
			handleFile("serii", "-1");
			FileWriter fstream = new FileWriter(pathFile.get("serii") + separator
					+ fileName, false);
			BufferedWriter out = new BufferedWriter(fstream);
			out.newLine();
			out.write("Proba " + heatList.get(0).getEventName());
			out.newLine();
			out.newLine();
			for (Heat heats : heatList) {
				out.write("Seria " + heats.getHeatNumber());
				out.newLine();
				if (heats.getLane1().equals(null)) {
					out.write("Culoar" + ";" + heats.getLane1().getLaneNumber());
					out.newLine();
				} else {
					out.write("Culoar"
							+ ";"
							+ heats.getLane1().getLaneNumber()
							+ ";"
							+ heats.getLane1().getSwimmer().getName()
							+ ";"
							+ heats.getLane1().getSwimmer().getClub()
							+ ";"
							+ heats.getLane1().getSwimmer().getAgeGroup()
							+ ";"
							+ padLeft(Integer.toString(heats.getLane1()
									.getEntryMinutes()), 2)
							+ ":"
							+ padLeft(Integer.toString(heats.getLane1()
									.getEntrySecondes()), 2)
							+ ":"
							+ padLeft(Integer.toString(heats.getLane1()
									.getEntryMSeconds()), 2));
					out.newLine();
				}
				out.write("Culoar"
						+ ";"
						+ heats.getLane2().getLaneNumber()
						+ ";"
						+ heats.getLane2().getSwimmer().getName()
						+ ";"
						+ heats.getLane2().getSwimmer().getClub()
						+ ";"
						+ heats.getLane2().getSwimmer().getAgeGroup()
						+ ";"
						+ padLeft(Integer.toString(heats.getLane2()
								.getEntryMinutes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane2()
								.getEntrySecondes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane2()
								.getEntryMSeconds()), 2));
				out.newLine();
				out.write("Culoar"
						+ ";"
						+ heats.getLane3().getLaneNumber()
						+ ";"
						+ heats.getLane3().getSwimmer().getName()
						+ ";"
						+ heats.getLane3().getSwimmer().getClub()
						+ ";"
						+ heats.getLane3().getSwimmer().getAgeGroup()
						+ ";"
						+ padLeft(Integer.toString(heats.getLane3()
								.getEntryMinutes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane3()
								.getEntrySecondes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane3()
								.getEntryMSeconds()), 2));
				out.newLine();
				out.write("Culoar"
						+ ";"
						+ heats.getLane4().getLaneNumber()
						+ ";"
						+ heats.getLane4().getSwimmer().getName()
						+ ";"
						+ heats.getLane4().getSwimmer().getClub()
						+ ";"
						+ heats.getLane4().getSwimmer().getAgeGroup()
						+ ";"
						+ padLeft(Integer.toString(heats.getLane4()
								.getEntryMinutes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane4()
								.getEntrySecondes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane4()
								.getEntryMSeconds()), 2));
				out.newLine();
				out.write("Culoar"
						+ ";"
						+ heats.getLane5().getLaneNumber()
						+ ";"
						+ heats.getLane5().getSwimmer().getName()
						+ ";"
						+ heats.getLane5().getSwimmer().getClub()
						+ ";"
						+ heats.getLane5().getSwimmer().getAgeGroup()
						+ ";"
						+ padLeft(Integer.toString(heats.getLane5()
								.getEntryMinutes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane5()
								.getEntrySecondes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane5()
								.getEntryMSeconds()), 2));
				out.newLine();
				out.write("Culoar"
						+ ";"
						+ heats.getLane6().getLaneNumber()
						+ ";"
						+ heats.getLane6().getSwimmer().getName()
						+ ";"
						+ heats.getLane6().getSwimmer().getClub()
						+ ";"
						+ heats.getLane6().getSwimmer().getAgeGroup()
						+ ";"
						+ padLeft(Integer.toString(heats.getLane6()
								.getEntryMinutes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane6()
								.getEntrySecondes()), 2)
						+ ":"
						+ padLeft(Integer.toString(heats.getLane6()
								.getEntryMSeconds()), 2));
				out.newLine();
				/**
				 * goes in here only for 50m pool
				 */
				if (poolType.contains("50")) {
					out.write("Culoar"
							+ ";"
							+ heats.getLane7().getLaneNumber()
							+ ";"
							+ heats.getLane7().getSwimmer().getName()
							+ ";"
							+ heats.getLane7().getSwimmer().getClub()
							+ ";"
							+ heats.getLane7().getSwimmer().getAgeGroup()
							+ ";"
							+ padLeft(Integer.toString(heats.getLane7()
									.getEntryMinutes()), 2)
							+ ":"
							+ padLeft(Integer.toString(heats.getLane7()
									.getEntrySecondes()), 2)
							+ ":"
							+ padLeft(Integer.toString(heats.getLane7()
									.getEntryMSeconds()), 2));
					out.newLine();
					out.write("Culoar"
							+ ";"
							+ heats.getLane8().getLaneNumber()
							+ ";"
							+ heats.getLane8().getSwimmer().getName()
							+ ";"
							+ heats.getLane8().getSwimmer().getClub()
							+ ";"
							+ heats.getLane8().getSwimmer().getAgeGroup()
							+ ";"
							+ padLeft(Integer.toString(heats.getLane8()
									.getEntryMinutes()), 2)
							+ ":"
							+ padLeft(Integer.toString(heats.getLane8()
									.getEntrySecondes()), 2)
							+ ":"
							+ padLeft(Integer.toString(heats.getLane8()
									.getEntryMSeconds()), 2));
					out.newLine();
				}
				out.newLine();
			}
			out.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public List<Result> returnResults(Event event, String heatGender,
			String requiredGender) {
		List<Result> results = new ArrayList<Result>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					pathFile.get("rezultate") + separator + "Rezultate "
							+ event.getName() + " " + heatGender + ".csv"));
			try {
				String line = null;
				while ((line = br.readLine()) != null) {
					String[] entry = line.split(";");
					// return the results based on the gender
					if (requiredGender.equals("M")
							|| requiredGender.equals("F")) {
						if (returnSwimmer(entry[0]).getGender().equals(
								requiredGender)) {
							results.add(new Result(returnSwimmer(entry[0]),
									Integer.valueOf(entry[3]), Integer
											.valueOf(entry[4]), Integer
											.valueOf(entry[5]), Long
											.valueOf(entry[6]), String
											.valueOf(entry[7])));
						}
					} else {
						results.add(new Result(returnSwimmer(entry[0]), Integer
								.valueOf(entry[3]), Integer.valueOf(entry[4]),
								Integer.valueOf(entry[5]), Long
										.valueOf(entry[6]), String
										.valueOf(entry[7])));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			return new ArrayList<Result>();
		}

		return results;
	}

	/**
	 * Returns the results for one swimmer, from the results list
	 */
	public Result getResultForSwimmer(List<Result> results, Swimmer swimmer) {
		for (Result res : results) {
			if (swimmer.getName().equals(res.getSwimmer().getName())) {
				return res;
			}
		}
		return new Result(new Swimmer(), 0, 0, 0, 0, new String());
	}

	/**
	 * Write the results into a ordered manner (order per age group and result)
	 * in a new file
	 * 
	 * @param results
	 * @param event
	 */
	public void generateResultTable(List<Result> results, Event event,
			String heatGender) {
		AgeGroup ageGroup = new AgeGroup();
		List<String> ageGroups = ageGroup.getAgeGroups();

		try {
			if (heatGender.equals("Mixt")) {
				// results for Mixt heatGender
				FileWriter fstreamM = new FileWriter(pathFile.get("rezultate")
						+ separator + "Rezultate Ordonate " + event.getName() + " "
						+ "M" + ".csv", false);
				FileWriter fstreamF = new FileWriter(pathFile.get("rezultate")
						+ separator + "Rezultate Ordonate " + event.getName() + " "
						+ "F" + ".csv", false);
				BufferedWriter outM = new BufferedWriter(fstreamM);
				BufferedWriter outF = new BufferedWriter(fstreamF);

				for (String age : ageGroups) {
					if (searchAgeGroupInResult(results, age, "M")) {
						outM.write(age);
						outM.newLine();
						for (Result res : results) {
							if (res.getSwimmer().getAgeGroup().equals(age)
									&& res.getSwimmer().getGender().equals("M")) {
								outM.write(res.getSwimmer().getName() + ";"
										+ res.getSwimmer().getClub() + ";"
										+ res.getSwimmer().getBirthYear() + ";"
										+ res.getResultMinutes() + ";"
										+ res.getResultSecondes() + ";"
										+ res.getResultMSeconds());
								outM.newLine();
							}
						}
						outM.newLine();
					}
					if (searchAgeGroupInResult(results, age, "F")) {
						outF.write(age);
						outF.newLine();
						for (Result res : results) {
							if (res.getSwimmer().getAgeGroup().equals(age)
									&& res.getSwimmer().getGender().equals("F")) {
								outF.write(res.getSwimmer().getName() + ";"
										+ res.getSwimmer().getClub() + ";"
										+ res.getSwimmer().getBirthYear() + ";"
										+ res.getResultMinutes() + ";"
										+ res.getResultSecondes() + ";"
										+ res.getResultMSeconds());
								outF.newLine();
							}
						}
						outF.newLine();
					}
				}
				outM.newLine();
				outM.close();
				outF.newLine();
				outF.close();
			} else {
				// results for defined heatGender: M or F
				FileWriter fstream = new FileWriter(pathFile.get("rezultate")
						+ separator + "Rezultate Ordonate " + event.getName() + " "
						+ heatGender + ".csv", false);
				BufferedWriter out = new BufferedWriter(fstream);

				for (String age : ageGroups) {
					if (searchAgeGroupInResult(results, age, heatGender)) {
						out.write(age);
						out.newLine();
						for (Result res : results) {
							if (res.getSwimmer().getAgeGroup().equals(age)
									&& res.getSwimmer().getGender()
											.equals(heatGender)) {
								out.write(res.getSwimmer().getName() + ";"
										+ res.getSwimmer().getClub() + ";"
										+ res.getSwimmer().getBirthYear() + ";"
										+ res.getResultMinutes() + ";"
										+ res.getResultSecondes() + ";"
										+ res.getResultMSeconds());
								out.newLine();
							}
						}
						out.newLine();
					}
				}
				out.newLine();
				out.close();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public boolean searchAgeGroupInResult(List<Result> results, String age,
			String heatGender) {
		for (Result res : results) {
			if (age.equals(res.getSwimmer().getAgeGroup())
					&& heatGender.equals(res.getSwimmer().getGender()
							.toString())) {
				return true;
			}
		}
		return false;
	}

	public void removeLineFromFile(String file, String lineToRemove) {
		try {
			File inFile = new File(file);
			if (!inFile.isFile()) {
				return;
			}
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String line = null;
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

	private String setZero(String value) {
		return (value.length() < 2) ? "0" + value : value;
	}

	private void handleFile(String dirType, String fileType) throws IOException {
		// in this case only the dir is required
		if (fileType.equals("-1")) {
			File dir = new File(pathFile.get(dirType));
			if (!dir.exists()) {
				dir.mkdir();
			}
		} else {
			File dir = new File(pathFile.get(dirType));
			if (!dir.exists()) {
				dir.mkdir();
				File file = new File(pathFile.get(dirType) + separator
						+ dataFile.get(fileType));
				file.createNewFile();
			} else {
				File file = new File(pathFile.get(dirType) + separator
						+ dataFile.get(fileType));
				if (!file.exists())
					file.createNewFile();
			}
		}
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

}
