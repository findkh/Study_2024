package algorithm;

public class SumArray {
	public static void main(String[] args) {
		//정수형 a 배열의 합계를 구하는 코드
		int[] a = {72, 68, 92, 88, 41, 53, 97, 84, 39, 55};
		int sum = 0;
		
		for(int i = 0; i < a.length; i++) {
			System.out.printf("i값: %d, 더하기 전 값: %d \n", i, sum);
			sum += a[i];
		}
		System.out.println("=================");
		System.out.println("합계: "+ sum);
	}
}
