package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import work.Operations;
import entities.Event;
import entities.Swimmer;

public class EventOperations {

    private List<String> eventNames = new ArrayList<String>();
    private Operations operations = new Operations();
    private List<String> minutes = new ArrayList<String>();
    private List<String> seconds = new ArrayList<String>();
    private List<String> mSecondes = new ArrayList<String>();
	private Map<String, String> dataFile = new Constants().getDataFiles();
	private Map<String, String> pathFile = new Constants().getDataFiles();
	
    public EventOperations() {
    	fillEventNames();
    	fillTimes();
    }
    
	private void fillEventNames() {
		List<Event> events = operations.returnAllEvents();
		for (Event event : events) {
			eventNames.add(event.getName());
		}		
	}
	
	private void fillTimes() {
		for (int i = 0; i < 60; i++) {
			minutes.add(padLeft(Integer.toString(i), 2));
			seconds.add(padLeft(Integer.toString(i), 2));
		}
		for (int i = 0; i < 100; i++) {
			mSecondes.add(padLeft(Integer.toString(i), 2));
		}
	}

	private static String padLeft(String s, int n) {
	    return String.format("%1$#" + n + "s", s).replace(' ', '0');  
	}

	public void registerEvent(Event event)  throws IOException {
		handleFile("core", "events");
		FileWriter fstream = new FileWriter(pathFile.get("core") + "\\" + dataFile.get("events"), true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(event.getName() + ";" + event.getLength() + ";" + event.getStyle()
				+ ";" + event.getPoolType());
		out.newLine();
		out.close();
	}
	
	public int getNumberOfEvent() {
		Scanner scanner;
		int eventNumber = 0;
		try {
			scanner = new Scanner(new File(pathFile.get("core") + "\\"
					+ dataFile.get("events")));
			try {
				while (scanner.hasNextLine()) {
					eventNumber++;
					scanner.nextLine();
				}
			} finally {
				scanner.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return eventNumber;
	}
	private void handleFile(String dirType, String fileType) throws IOException {
		File dir = new File(pathFile.get(dirType));
		if (!dir.exists()) {
			dir.mkdir();
			File file = new File(pathFile.get(dirType) + "\\" + dataFile.get(fileType));
			file.createNewFile();
		}
		else {
			File file = new File(pathFile.get(dirType) + "\\" + dataFile.get(fileType));
			if (!file.exists()) 
				file.createNewFile();
		}
	}

	public List<String> getEventNames() {
		return eventNames;
	}

	public void setEventNames(List<String> eventNames) {
		this.eventNames = eventNames;
	}

	public List<String> getMinutes() {
		return minutes;
	}

	public void setMinutes(List<String> minutes) {
		this.minutes = minutes;
	}

	public List<String> getSeconds() {
		return seconds;
	}

	public void setSeconds(List<String> seconds) {
		this.seconds = seconds;
	}

	public List<String> getmSecondes() {
		return mSecondes;
	}

	public void setmSecondes(List<String> mSecondes) {
		this.mSecondes = mSecondes;
	}

}
