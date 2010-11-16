package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
	
	private Map<String, String> dataFiles = new HashMap<String, String>();
	private Map<String, String> styleNames = new HashMap<String, String>();
	
	public Constants() {
		declareDataFiles();
		declareStyleNames();
	}
	
	private void declareDataFiles() {
		this.dataFiles.put("swimmers", "swimmers.csv");
		this.dataFiles.put("events", "events.csv");
		this.dataFiles.put("registrations", "registrations.csv");
		this.dataFiles.put("heats", "heats.csv");
	}
	
	private void declareStyleNames() {
		this.styleNames.put("FREE", "freestyle");
		this.styleNames.put("BACK", "backstroke");
		this.styleNames.put("BREAST", "breaststroke");
		this.styleNames.put("FLY", "butterfly");
		this.styleNames.put("MEDLEY", "individual medley");
	}

	public Map<String, String> getDataFiles() {
		return dataFiles;
	}

	public void setDataFiles(Map<String, String> dataFiles) {
		this.dataFiles = dataFiles;
	}

	public Map<String, String> getStyleNames() {
		return styleNames;
	}

	public void setStyleNames(Map<String, String> styleNames) {
		this.styleNames = styleNames;
	}

}
