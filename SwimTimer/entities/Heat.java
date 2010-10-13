package entities;

import java.util.List;

public class Heat  implements Comparable<Heat>{

	private Lane lane1;
	private Lane lane2;
	private Lane lane3;
	private Lane lane4;
	private Lane lane5;
	private Lane lane6;
	private Lane lane7;
	private Lane lane8;
	private String eventName;
	private int heatNumber;
	private int heatOcupants;

	public Heat() {
	}

	public Heat(String eventName, int heatNumber, List<Lane> laneList) {

		setEventName(eventName);
		setHeatNumber(heatNumber);
		setHeatOcupants(laneList.size());
		
		if (getHeatOcupants() == 8) {
			setLane1(laneList.get(0));
			this.lane1.setLaneNumber(1);
			setLane2(laneList.get(1));
			this.lane2.setLaneNumber(2);
			setLane3(laneList.get(2));
			this.lane3.setLaneNumber(3);
			setLane4(laneList.get(3));
			this.lane4.setLaneNumber(4);
			setLane5(laneList.get(4));
			this.lane5.setLaneNumber(5);
			setLane6(laneList.get(5));
			this.lane6.setLaneNumber(6);
			setLane7(laneList.get(6));
			this.lane7.setLaneNumber(7);
			setLane8(laneList.get(7));
			this.lane8.setLaneNumber(8);
		} else {
			setLane1(laneList.get(0));
			this.lane1.setLaneNumber(1);
			setLane2(laneList.get(1));
			this.lane2.setLaneNumber(2);
			setLane3(laneList.get(2));
			this.lane3.setLaneNumber(3);
			setLane4(laneList.get(3));
			this.lane4.setLaneNumber(4);
			setLane5(laneList.get(4));
			this.lane5.setLaneNumber(5);
			setLane6(laneList.get(5));
			this.lane6.setLaneNumber(6);
		}
	}

	public int compareTo(Heat o1) {
        if (this.heatNumber == o1.heatNumber)
            return 0;
        else if ((this.heatNumber) > o1.heatNumber)
            return 1;
        else
            return -1;
    }
	
	public Lane getLane1() {
		return lane1;
	}

	public void setLane1(Lane lane1) {
		this.lane1 = lane1;
	}

	public Lane getLane2() {
		return lane2;
	}

	public void setLane2(Lane lane2) {
		this.lane2 = lane2;
	}

	public Lane getLane3() {
		return lane3;
	}

	public void setLane3(Lane lane3) {
		this.lane3 = lane3;
	}

	public Lane getLane4() {
		return lane4;
	}

	public void setLane4(Lane lane4) {
		this.lane4 = lane4;
	}

	public Lane getLane5() {
		return lane5;
	}

	public void setLane5(Lane lane5) {
		this.lane5 = lane5;
	}

	public Lane getLane6() {
		return lane6;
	}

	public void setLane6(Lane lane6) {
		this.lane6 = lane6;
	}

	public Lane getLane7() {
		return lane7;
	}

	public void setLane7(Lane lane7) {
		this.lane7 = lane7;
	}

	public Lane getLane8() {
		return lane8;
	}

	public void setLane8(Lane lane8) {
		this.lane8 = lane8;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getHeatNumber() {
		return heatNumber;
	}

	public void setHeatNumber(int heatNumber) {
		this.heatNumber = heatNumber;
	}

	public int getHeatOcupants() {
		return heatOcupants;
	}

	public void setHeatOcupants(int heatOcupants) {
		this.heatOcupants = heatOcupants;
	}

}
