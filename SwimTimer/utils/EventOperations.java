package utils;

import java.util.ArrayList;
import java.util.List;

import work.Operations;
import entities.Event;
import entities.Swimmer;

public class EventOperations {

    private List<String> eventNames = new ArrayList<String>();
    private Operations operations = new Operations();
    private List<String> minutes = new ArrayList<String>();
    private List<String> seconds = new ArrayList<String>();
    private List<String> mSecondes = new ArrayList<String>();
	
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
