package entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.util.Map;

import utils.Constants;

public class Registration {

	private Swimmer swimmer;
	private Event event;
	private long entryTime;
	private Integer minutes;
	private Integer seconds;
	private Integer mSeconds;

	private Map<String, String> dataFile = new Constants().getDataFiles();

	public Registration(Swimmer swimmer, Event event, Integer minutes,
			Integer seconds, Integer mSeconds) {
		setSwimmer(swimmer);
		setEvent(event);
		setMinutes(minutes);
		setSeconds(seconds);
		setmSeconds(mSeconds);
		setEntryTime();
	}

	public String registerRegistration() {
		String result = "OK";
		try {
			FileWriter fstream = new FileWriter(dataFile.get("registrations")
					.toString(), true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(getSwimmer().getName() + ";"
					+ getSwimmer().getBirthYear() + ";"
					+ getSwimmer().getAgeGroup() + ";"
					+ getSwimmer().getGender() + ";" + getSwimmer().getClub()
					+ ";" + getEvent().getName() + ";"
					+ padLeft(Integer.toString(getMinutes()), 2) + ";"
					+ padLeft(Integer.toString(getSeconds()), 2) + ";"
					+ padLeft(Integer.toString(getmSeconds()), 2));
			out.newLine();
			out.close();
			result = "Registration Added";
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}

	private static String padLeft(String s, int n) {
		return String.format("%1$#" + n + "s", s).replace(' ', '0');
	}

	public Swimmer getSwimmer() {
		return swimmer;
	}

	public void setSwimmer(Swimmer swimmer) {
		this.swimmer = swimmer;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public long getEntryTime() {
		return entryTime;
	}

	private void setEntryTime() {
		this.entryTime = (long) ((this.minutes * 60 * 1000)
				+ (this.seconds * 1000) + this.mSeconds);
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}

	public Integer getmSeconds() {
		return mSeconds;
	}

	public void setmSeconds(Integer mSeconds) {
		this.mSeconds = mSeconds;
	}

}
