package work;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Event;

public class TestStuff {
	
	private int id;
	
	public TestStuff(int id) {
		setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public long transformTime(Integer minutes, Integer seconds, Integer mSeconds) {
		return (long)((minutes * 60 * 100) + (seconds * 100) + mSeconds);
	}
	
	public static void main(String args[]) {
//		List<TestStuff> testList = new ArrayList<TestStuff>();
//		int i = 0;
//		while (i < 5) {
//		//for (int i = 0; i < 5; i++) {
//			TestStuff test = new TestStuff(i);
//			testList.add(test);
//			i++;
//		}
//		for (TestStuff a : testList) {
//			System.out.println("id = " + a.getId());
//		}
		
		Date d;
		
		Integer minutes = 1; 
		Integer seconds = 10; 
		Integer mSeconds = 21; 
		
		long x = (long)70050;
		
		System.out.println(x/100);
	}
}
