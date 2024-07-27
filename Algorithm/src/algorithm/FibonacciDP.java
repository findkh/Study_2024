package algorithm;

public class FibonacciDP {
	/* 동적 계획법(Dynamic Programming)
	 * - 같은 인수이면 반환값도 동일하므로 여러 번 같은 인수로 fibonacci 함수를 호출하는것은 낭비이다.
	 * - 동적 계획법은 문할한 문제의 답을 기억해 두고, 이를 재사용함으로써 같은 문제를 여러 번 푸는 낭비를 방지한다.
	 * */
	
	//피보나치 수를 기억하는 배열
	public static int[] fibonacciNumbers = new int[100];
	
	//인수 n의 피보나치 수를 반환하는 메소드
	public static int fibonacci(int n) {
		for(int i = 0; i <= n; i++) {
			if(i == 0) {
				fibonacciNumbers[i] = 0;
			} else if(i == 1) {
				fibonacciNumbers[i] = 1;
			} else {
				fibonacciNumbers[i] = fibonacciNumbers[i - 1] + fibonacciNumbers[i - 2];
			}
		}
		return fibonacciNumbers[n];
	}
	
	public static void main(String[] args) {
		for(int n = 0; n <= 8; n++) {
			System.out.printf("%d, ", fibonacci(n));
		}
		System.out.printf("\n");
	}
}
