package entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;

import utils.Constants;

public class Event {

	private String name;
	private String length;
	private String style;
	private String gender;
	private String poolType;

	private static Event instance = null;

	private Map<String, String> dataFile = new Constants().getDataFiles();
	
	public Event() {
		setName("");
		setLength("");
		setStyle("");
		setGender("");
		setPoolType("");   
	}

	public Event(String name, String length, String style, String gender,
			String poolType) {
		setName(name);
		setLength(length);
		setStyle(style);
		setGender(gender);
		setPoolType(poolType);
	}

	public Event getInstance() {
		if (instance == null) {
			instance = new Event(this.name, this.length, this.style,
					this.gender, this.poolType);
		}
		return instance;
	}

	public String registerEvent() {
		String result = "OK";
		try {
			FileWriter fstream = new FileWriter(dataFile.get("events")
					.toString(), true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(getName() + ";" + getLength() + ";" + getStyle()
					+ ";" + getGender() + ";" + getPoolType());
			out.newLine();
			out.close();
			result = "Event Added";
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPoolType() {
		return poolType;
	}

	public void setPoolType(String poolType) {
		this.poolType = poolType;
	}

}
