package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import work.Operations;
import entities.Swimmer;

public class SwimmerOperations {

    private List<String> birthYears = new ArrayList<String>();
    private List<String> swimmerNames = new ArrayList<String>();
    private Operations operations = new Operations();
	private Map<String, String> dataFile = new Constants().getDataFiles();
	private Map<String, String> pathFile = new Constants().getDataFiles();
	
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

	public void registerSwimmer(Swimmer swimmer) throws IOException {
		FileWriter fstream;
		handleFile("core", "swimmers");
		fstream = new FileWriter(pathFile.get("core") + "\\" + dataFile.get("swimmers"), true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(swimmer.getName() + ";" + swimmer.getBirthYear() + ";"
				+ swimmer.getAgeGroup() + ";" + swimmer.getGender() + ";" + swimmer.getClub());
		out.newLine();
		out.close();
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
