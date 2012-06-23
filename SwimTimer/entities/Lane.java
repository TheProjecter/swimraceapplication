package entities;

public class Lane {

	private Swimmer swimmer;
	private Event event;
	private long registeredTime;
	private Integer resultMinutes;
	private Integer resultSecondes;
	private Integer resultMSeconds;
	private long resultTime;
	private Integer entryMinutes;
	private Integer entrySecondes;
	private Integer entryMSeconds;
	private int laneNumber;
	private String performanceStatus;

	public Lane() {
		this.swimmer = new Swimmer();
		this.event = new Event();
	}

	public Lane(Swimmer swimmer, Event event, long registeredTime,
			Integer entryMinutes, Integer entrySeconds, Integer entryMSeconds) {
		setSwimmer(swimmer);
		setEvent(event);
		setRegisteredTime(registeredTime);
		setEntryMinutes(entryMinutes);
		setEntrySecondes(entrySeconds);
		setEntryMSeconds(entryMSeconds);
	}

	public void setResultTime() {
		this.resultTime = (long) ((this.resultMinutes * 60 * 1000)
				+ (this.resultSecondes * 1000) + this.resultMSeconds);
	}
	
	public void setResultTime(long resultTime) {
		this.resultTime = resultTime;
	}

	public Swimmer getSwimmer() {
		return swimmer;
	}

	public void setSwimmer(Swimmer swimmer) {
		this.swimmer = swimmer;
	}

	public long getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(long registeredTime) {
		this.registeredTime = registeredTime;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Integer getResultMinutes() {
		return resultMinutes;
	}

	public void setResultMinutes(Integer resultMinutes) {
		this.resultMinutes = resultMinutes;
	}

	public Integer getResultSecondes() {
		return resultSecondes;
	}

	public void setResultSecondes(Integer resultSecondes) {
		this.resultSecondes = resultSecondes;
	}

	public Integer getResultMSeconds() {
		return resultMSeconds;
	}

	public void setResultMSeconds(Integer resultMSeconds) {
		this.resultMSeconds = resultMSeconds;
	}

	public int getLaneNumber() {
		return laneNumber;
	}

	public void setLaneNumber(int laneNumber) {
		this.laneNumber = laneNumber;
	}

	public Integer getEntryMinutes() {
		return entryMinutes;
	}

	public void setEntryMinutes(Integer entryMinutes) {
		this.entryMinutes = entryMinutes;
	}

	public Integer getEntrySecondes() {
		return entrySecondes;
	}

	public void setEntrySecondes(Integer entrySecondes) {
		this.entrySecondes = entrySecondes;
	}

	public Integer getEntryMSeconds() {
		return entryMSeconds;
	}

	public void setEntryMSeconds(Integer entryMSeconds) {
		this.entryMSeconds = entryMSeconds;
	}

	public long getResultTime() {
		return resultTime;
	}

	public String getPerformanceStatus() {
		return performanceStatus;
	}

	public void setPerformanceStatus(String performanceStatus) {
		this.performanceStatus = performanceStatus;
	}
}
