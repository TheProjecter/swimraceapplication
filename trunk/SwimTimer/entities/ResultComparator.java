package entities;

import java.util.Comparator;

public class ResultComparator implements Comparator<Result> {

	@Override
	public int compare(Result arg0, Result arg1) {
		if (arg0.getResultTime() == arg1.getResultTime())
			return 0;
		else if ((arg0.getResultTime()) > arg1.getResultTime())
			return 1;
		else
			return -1;
	}
}