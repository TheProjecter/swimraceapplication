package entities;

import java.util.Comparator;

public class LaneComparator implements Comparator<Lane> {

	@Override
	public int compare(Lane arg0, Lane arg1) {
		if (arg0.getRegisteredTime() == arg1.getRegisteredTime())
			return 0;
		else if ((arg0.getRegisteredTime()) > arg1.getRegisteredTime())
			return 1;
		else
			return -1;
	}
}