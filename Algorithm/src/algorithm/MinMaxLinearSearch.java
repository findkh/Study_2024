package algorithm;

public class MinMaxLinearSearch {
	public static void main(String[] args) {
		int [] a = {72, 68, 92, 88, 41, 53, 97, 84, 39, 55};
		int maxNum = a[0];
		int minNum= a[0];
		
		for(int i = 0; i < a.length; i++) {
			if(maxNum < a[i]) {
				maxNum = a[i];
			}
			
			if(minNum > a[i]) {
				minNum = a[i];
			}
		}
		
		System.out.println("최댓값: " + maxNum);
		System.out.println("최솟값: " + minNum);
	}
}
