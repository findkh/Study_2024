package algorithm;

public class Fibonacci {
	public static int fibonacci(int n) {
		if(n == 0) {
			return 0;
		} else if( n == 1) {
			return 1;
		} else {
			return fibonacci(n-1) + fibonacci(n-2);
		}
	}
	
	public static void main(String[] args) {
		int n;
		
		for(n = 0; n <= 8; n++) {
			System.out.printf("%d ", fibonacci(n));
		}
		
		System.out.printf("\n");
	}
}
