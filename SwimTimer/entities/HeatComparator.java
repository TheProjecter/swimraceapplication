package entities;

import java.util.Comparator;

public class HeatComparator implements Comparator<Heat> {

	@Override
	public int compare(Heat arg0, Heat arg1) {
		if (arg0.getHeatNumber() == arg1.getHeatNumber())
			return 0;
		else if ((arg0.getHeatNumber()) > arg1.getHeatNumber())
			return 1;
		else
			return -1;
	}
}