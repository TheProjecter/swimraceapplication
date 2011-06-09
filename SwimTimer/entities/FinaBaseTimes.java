package entities;

public class FinaBaseTimes {

	private String year;
	private String poolType;
	private String gender;
	private int relayCount;
	private String length;
	private String style;
	private Integer minutes;
	private Integer seconds;
	private Integer mSeconds;
	private long swimTime;
	private double baseTimesSeconds;
	private String ageGroup;

	public FinaBaseTimes(String year, String poolType, String gender,
			String ageGroup, int relayCount, String length, String style,
			Integer minutes, Integer seconds, Integer mSeconds,
			double baseTimesSeconds) {
		setYear(year);
		setPoolType(poolType);
		setGender(gender);
		setAgeGroup(ageGroup);
		setRelayCount(relayCount);
		setLength(length);
		setStyle(style);
		setMinutes(minutes);
		setSeconds(mSeconds);
		setmSeconds(mSeconds);
		setBaseTimesSeconds(baseTimesSeconds);
		setSwimTime();
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPoolType() {
		return poolType;
	}

	public void setPoolType(String poolType) {
		this.poolType = poolType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getRelayCount() {
		return relayCount;
	}

	public void setRelayCount(int relayCount) {
		this.relayCount = relayCount;
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

	public long getSwimTime() {
		return swimTime;
	}

	private void setSwimTime() {
		this.swimTime = (long) ((this.minutes * 60 * 1000)
				+ (this.seconds * 1000) + this.mSeconds);
	}

	public double getBaseTimesSeconds() {
		return baseTimesSeconds;
	}

	public void setBaseTimesSeconds(double baseTimesSeconds) {
		this.baseTimesSeconds = baseTimesSeconds;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

}
