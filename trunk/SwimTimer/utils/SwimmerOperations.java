package utils;

import java.util.ArrayList;
import java.util.List;

import work.Operations;

import entities.Swimmer;

public class SwimmerOperations {

    private List<String> birthYears = new ArrayList<String>();
    private List<String> swimmerNames = new ArrayList<String>();
    private Operations operations = new Operations();
	
    public SwimmerOperations() {
    	fillBirthYears();
    	fillSwimmerNames();
    }
    
	public String ageGroupCalculation(int age) {
		String ageGroup = "18 - ";
		for (int i = 18; i<120; i++) {
			if (i%5 == 0 && (age - i <= 4) && (age - i >= 0)) {
				ageGroup = "" + i + " - ";
			}
			if ((i - age <= 4) && i%5 == 4 && (i - age >= 0)) {
				ageGroup = ageGroup + i;
			}
		}
		
		return ageGroup;
	}
	
	private void fillSwimmerNames() {
		List<Swimmer> swimmers = operations.returnAllSwimmers();
		for (Swimmer sw : swimmers) {
			swimmerNames.add(sw.getName());
		}		
	}
	private void fillBirthYears() {
        for (int i = 1992; i > 1900; i--) {
        	birthYears.add(Integer.toString(i));
        }
    }

	public List<String> getBirthYears() {
		return birthYears;
	}

	public void setBirthYears(List<String> birthYears) {
		this.birthYears = birthYears;
	}

	public List<String> getSwimmerNames() {
		return swimmerNames;
	}

	public void setSwimmerNames(List<String> swimmerNames) {
		this.swimmerNames = swimmerNames;
	}

}
