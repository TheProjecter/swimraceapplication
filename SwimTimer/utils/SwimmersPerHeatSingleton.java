package utils;

import java.util.HashMap;
import java.util.Map;

import entities.Event;

public class SwimmersPerHeatSingleton {
	private static SwimmersPerHeatSingleton uniqueInstance;
	private Map<String, Integer> swimmerPerHeatMapping = new HashMap<String, Integer>();
	
	private SwimmersPerHeatSingleton() {
	}
	
	public static SwimmersPerHeatSingleton getInstance() {
		if (uniqueInstance == null) {
			synchronized (SwimmersPerHeatSingleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new SwimmersPerHeatSingleton();
				}
			}
		}
		return uniqueInstance;
	}

	public void push(String heat, Integer number) {
		if (swimmerPerHeatMapping.containsKey(heat)) {
			swimmerPerHeatMapping.remove(heat);
		}
		swimmerPerHeatMapping.put(heat, number);
	}

	public void clear() {
		swimmerPerHeatMapping.clear();
	}
	
	public Integer getValue(String heat) {
		return swimmerPerHeatMapping.get(heat);
	}
	
	public Map<String, Integer> getSwimmerPerHeatMapping() {
		return swimmerPerHeatMapping;
	}

}
