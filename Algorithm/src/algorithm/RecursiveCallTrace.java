package algorithm;

public class RecursiveCallTrace {
/* 재귀호출(Recursive Call)
 * 함수 안에서 자기 자신을 호출하여 반복 처리를 하는 것.
 * 재귀 호출을 수행하는 함수는 재귀 호출을 종료하는 조건을 준비해야한다.
 * 재귀 호출은 처리에 시간이 오래 걸리고 메모리를 많이 소모한다.
 * */
	
	public static int factorial(int n) {
		System.out.printf("factorial(%d)이(가) 호출되었습니다.\n", n);
		
		if(n == 0) {
			System.out.printf("factorial(0)이 1을 반환하였습니다. \n");
			return 1;
		} else {
			int retVal = n * factorial(n - 1);
			System.out.printf("factorial(%d)이(가) %d을(를) 반환했습니다.\n", n, retVal);
			return retVal;
		}
	}
	
	public static void main(String[] args) {
		int ans;
		
		ans = factorial(5);
		System.out.printf("%d\n", ans);
	}
}
