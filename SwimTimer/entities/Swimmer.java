package entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;

import utils.Constants;

public class Swimmer {

	private String name;
	private String birthYear;
	private String ageGroup;
	private String gender;
	private String club;

	private static Swimmer instance = null;

	private Map<String, String> dataFile = new Constants().getDataFiles();
	
	public Swimmer() {
		setName("");
		setBirthYear("");
		setAgeGroup("");
		setGender("");
		setClub("");
	}

	public Swimmer(String name, String birthYear, String ageGroup,
			String gender, String club) {
		setName(name);
		setBirthYear(birthYear);
		setAgeGroup(ageGroup);
		setGender(gender);
		setClub(club);
	}

	public Swimmer getInstance() {
		if (instance == null) {
			instance = new Swimmer(this.name, this.birthYear, this.ageGroup,
					this.gender, this.club);
		}
		return instance;
	}

	public String registerSwimmer() {
		String result = "OK";
		try {
			FileWriter fstream = new FileWriter(dataFile.get("swimmers")
					.toString(), true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(getName() + ";" + getBirthYear() + ";"
					+ getAgeGroup() + ";" + getGender() + ";" + getClub());
			out.newLine();
			out.close();
			result = "Swimmer Added";
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

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
