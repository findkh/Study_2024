package sort;

import java.util.Arrays;

public class DuckSortTest {
	public static void main(String[] args) {
		Duck[] ducks = {
				new Duck("sudal", 8),
				new Duck("hafa", 1),
				new Duck("ggoggo", 4),
				new Duck("haha", 10),
				new Duck("happy", 3)
		};
		
		System.out.println("정렬 전: ");
		display(ducks);
		
		System.out.println("==============");
		
		Arrays.sort(ducks);
		System.out.println("정렬 후: ");
		display(ducks);
	}

	public static void display(Duck[] ducks) {
		for(Duck d: ducks) {
			System.out.println(d);
		}
	}
	
	
}
