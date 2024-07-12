package algorithm;

public class Gugudan {
	public static void main(String[] args) {
		for(int x = 2; x <= 9; x++) {
			System.out.printf("%d단", x);
			
			for(int y = 1; y <= 9; y++) {
				System.out.printf("\t%d", x*y);
			}
			System.out.printf("\n");
		}
	}
}
