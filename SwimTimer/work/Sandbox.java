package work;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import pdfWriter.HeatListWriter;
import pdfWriter.ResultWriter;

import utils.Calculations;
import utils.Constants;
import utils.SwimmersPerHeatSingleton;

import entities.Event;
import entities.FinaBaseTimes;
import entities.Heat;
import entities.Lane;
import entities.LaneComparator;
import entities.Registration;
import entities.Swimmer;

public class Sandbox {
	private static Map<String, String> dataFile = new Constants().getDataFiles();
	private static Map<String, String> pathFile = new Constants().getDataFiles();
	private final List<String> list = new ArrayList<String>();
	Operations operations = new Operations();
	
	public void push() {
		list.add("value");
	}
	public void display() {
		for (String i : list) {
			System.out.println(i);
		}
	}
	public static void main(String[] args) throws IOException {
		SwimmersPerHeatSingleton swimPerHeat = SwimmersPerHeatSingleton.getInstance();
		swimPerHeat.push("sprint", 4);
		swimPerHeat.push("testNR", 6);

		swimPerHeat.push("sprint", 3);
		swimPerHeat.push("testNR", 7);
		
		System.out.println(swimPerHeat.getValue("sprint"));	

		for (Map.Entry<String, Integer> entry: swimPerHeat.getSwimmerPerHeatMapping().entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());	
		}

//		s.push();
//		s.display();
//		System.out.println("------");
//		s.push();
//		s.display();
//		String result;
//		Constants con = new Constants();
//		
//		File dir = new File(pathFile.get("core"));
//		if (!dir.exists()) {
//			dir.mkdir();
//			File file = new File(pathFile.get("core") + "\\" + dataFile.get("swimmers"));
//			file.createNewFile();
//		}
//		else {
//			File file = new File(pathFile.get("core") + "\\" + dataFile.get("swimmers"));
//			if (!file.exists()) 
//				file.createNewFile();
//		}			
//		Swimmer swimmer = new Swimmer("Cezar", "1981", "25-29", "M",
//		"Masters Timisoara").getInstance();
//		result = swimmer.registerSwimmer();
//		System.out.println(result);

		// Event event = new Event("400 liber femei", "400", "liber", "F",
		// "50m").getInstance();
		// result = event.registerEvent();
		// System.out.println(result);

		// operations
//		Operations sOps = new Operations();

		// // sOps.clearDB("swimmers");
		// List<Swimmer> swList = new ArrayList<Swimmer>();
		// List<Event> eventList = new ArrayList<Event>();

		// swList = sOps.returnSwimmers();
		// for (Swimmer a : swList) {
		// String x = a.getName();
		// System.out.println(x);
		// }

		// eventList = sOps.returnEvents();
		// for (Event a : eventList) {
		// String x = a.getName();
		// System.out.println(x);
		// }

		// Swimmer swimmer = sOps.returnSwimmer("roly");
		// System.out.println(swimmer.getName());
		// System.out.println(swimmer.getBirthYear());
		// System.out.println(swimmer.getAgeGroup());
		// System.out.println(swimmer.getGender());
		// System.out.println(swimmer.getClub());

		// Event event = sOps.returnEvent("400 liber barbati");
		// System.out.println(event.getName());
		// System.out.println(event.getLength());
		// System.out.println(event.getStyle());
		// System.out.println(event.getGender());
		// System.out.println(event.getPoolType());

		// Registration registration;
		//		
		// registration = new Registration(sOps.returnSwimmer("Eugen"), sOps
		// .returnEvent("50 fluture barbati"), 0, 38, 43);
		// registration.registerRegistration();
		// registration = new Registration(sOps.returnSwimmer("Casian"), sOps
		// .returnEvent("50 fluture barbati"), 0, 37, 11);
		// registration.registerRegistration();

		// List<Lane> laneList = new ArrayList<Lane>();
		// List<Lane> lanesOnHeat = new ArrayList<Lane>();
		// Event event = sOps.returnEvent("100 liber barbati");
		// laneList = sOps.createLanes(event);
		//		
		// List ts1 = new ArrayList();
		//		
		// Collections.sort(laneList, new LaneComparator());
		// int heatCount = (laneList.size())/8 + 1;
		// int startPos = 0;
		// int endPos = 8;
		//		
		// System.out.println(laneList.size()+ " | " + heatCount);
		// for (int i = heatCount; i > 0; i--) {
		// System.out.println();
		// System.out.println("Heat number  " + i + " : ");
		// lanesOnHeat = laneList.subList(startPos, endPos);
		//			
		// lanesOnHeat = sOps.setLanesInOrder(lanesOnHeat);
		//			
		// for (Lane lanes : lanesOnHeat) {
		// System.out.println(lanes.getSwimmer().getName() + " -> "
		// + lanes.getEvent().getStyle() + " -> "
		// + lanes.getRegisteredTime());
		// }
		// startPos = startPos + 8;
		// endPos = ((endPos + 8) < laneList.size()) ? (endPos + 8) :
		// laneList.size();
		// }
//		List<Event> swimmers = sOps.returnAllEvents();
//		for (Event ev : swimmers) {
//			System.out.println(ev.getName());
//		}

//		HeatListWriter hWriter = new HeatListWriter(sOps.returnEvent("100 liber barbati"), "something good");
//		hWriter.run();

//		ResultWriter hWriter = new ResultWriter(sOps.returnEvent("50 liber"), "something good");
//		hWriter.run();
//		Event event = sOps.returnEvent("100 liber barbati");
//		List<Heat> heatList = sOps.generateHeats(event);
//		sOps.registerHeats(heatList, event.getName() + ".csv");
//		Calculations calc = Calculations.getInstance();
//		System.out.println(calc.getMinuteFromString("2:04.19"));
//		System.out.println(calc.getSecondsFromString("2:04.19"));
//		System.out.println(calc.getMSecondsFromString("2:04.19"));

//		List<FinaBaseTimes> fina = calc.getAllBaseTimes();
//		for (FinaBaseTimes times : fina) {
//			System.out.println(times.getLength() + "	" + times.getStyle() + "	" + times.getBaseTimesSeconds());
//		}
//		double fina = calc.getBaseTimeForEvent(sOps.returnEvent("100 freestyle barbati"));
//		System.out.println(fina);
//		System.out.println(calc.calculateLocalPoints(8));
		
		//sOps.removeLineFromFile("textfile.txt", "b");
		// for (Heat heats : heatList) {
		// System.out.println("Heat " + heats.getHeatNumber());
		//
		// System.out.println(heats.getEventName() + " -> lane "
		// + heats.getLane1().getLaneNumber() + " -> "
		// + heats.getLane1().getSwimmer().getName() + " -> "
		// + heats.getLane1().getEntryMinutes() + "."
		// + heats.getLane1().getEntrySecondes() + ":"
		// + heats.getLane1().getEntryMSeconds());
		// System.out.println(heats.getEventName() + " -> lane "
		// + heats.getLane2().getLaneNumber() + " -> "
		// + heats.getLane2().getSwimmer().getName() + " -> "
		// + heats.getLane2().getEntryMinutes() + "."
		// + heats.getLane2().getEntrySecondes() + ":"
		// + heats.getLane2().getEntryMSeconds());
		// System.out.println(heats.getEventName() + " -> lane "
		// + heats.getLane3().getLaneNumber() + " -> "
		// + heats.getLane3().getSwimmer().getName() + " -> "
		// + heats.getLane3().getEntryMinutes() + "."
		// + heats.getLane3().getEntrySecondes() + ":"
		// + heats.getLane3().getEntryMSeconds());
		// System.out.println(heats.getEventName() + " -> lane "
		// + heats.getLane4().getLaneNumber() + " -> "
		// + heats.getLane4().getSwimmer().getName() + " -> "
		// + heats.getLane4().getEntryMinutes() + "."
		// + heats.getLane4().getEntrySecondes() + ":"
		// + heats.getLane4().getEntryMSeconds());
		// System.out.println(heats.getEventName() + " -> lane "
		// + heats.getLane5().getLaneNumber() + " -> "
		// + heats.getLane5().getSwimmer().getName() + " -> "
		// + heats.getLane5().getEntryMinutes() + "."
		// + heats.getLane5().getEntrySecondes() + ":"
		// + heats.getLane5().getEntryMSeconds());
		// System.out.println(heats.getEventName() + " -> lane "
		// + heats.getLane6().getLaneNumber() + " -> "
		// + heats.getLane6().getSwimmer().getName() + " -> "
		// + heats.getLane6().getEntryMinutes() + "."
		// + heats.getLane6().getEntrySecondes() + ":"
		// + heats.getLane6().getEntryMSeconds());
		// System.out.println(heats.getEventName() + " -> lane "
		// + heats.getLane7().getLaneNumber() + " -> "
		// + heats.getLane7().getSwimmer().getName() + " -> "
		// + heats.getLane7().getEntryMinutes() + "."
		// + heats.getLane7().getEntrySecondes() + ":"
		// + heats.getLane7().getEntryMSeconds());
		// System.out.println(heats.getEventName() + " -> lane "
		// + heats.getLane8().getLaneNumber() + " -> "
		// + heats.getLane8().getSwimmer().getName() + " -> "
		// + heats.getLane8().getEntryMinutes() + "."
		// + heats.getLane8().getEntrySecondes() + ":"
		// + heats.getLane8().getEntryMSeconds());
		//
		// }
		
	}
}
