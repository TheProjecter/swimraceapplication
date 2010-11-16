package entities;

public class Result {

	private Swimmer swimmer;
	private Integer resultMinutes;
	private Integer resultSecondes;
	private Integer resultMSeconds;
	private long resultTime;
	private double swimTimeSeconds;

	public Result(Swimmer swimmer, Integer resultMinutes,
			Integer resultSecondes, Integer resultMSeconds, long resultTime) {
		setSwimmer(swimmer);
		setResultMinutes(resultMinutes);
		setResultSecondes(resultSecondes);
		setResultMSeconds(resultMSeconds);
		setResultTime(resultTime);
		setSwimTimeSeconds();
	}

	public Swimmer getSwimmer() {
		return swimmer;
	}

	public void setSwimmer(Swimmer swimmer) {
		this.swimmer = swimmer;
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

	public long getResultTime() {
		return resultTime;
	}

	public void setResultTime(long resultTime) {
		this.resultTime = resultTime;
	}

	public double getSwimTimeSeconds() {
		return swimTimeSeconds;
	}

	private void setSwimTimeSeconds() {
		this.swimTimeSeconds = (this.resultMinutes * 60) + this.resultSecondes
				+ (double) this.resultMSeconds / 100;
	}

}
