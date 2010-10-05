package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
	
	private Map<String, String> dataFiles = new HashMap<String, String>();
	
	public Constants() {
		this.dataFiles.put("swimmers", "swimmers.csv");
		this.dataFiles.put("events", "events.csv");
		this.dataFiles.put("registrations", "registrations.csv");
		this.dataFiles.put("heats", "heats.csv");
	}

    public Map<String, String> getDataFiles() {
		return dataFiles;
	}

	public void setDataFiles(Map<String, String> dataFiles) {
		this.dataFiles = dataFiles;
	}

}
