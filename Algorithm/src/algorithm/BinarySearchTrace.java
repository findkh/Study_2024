package algorithm;

import java.util.Scanner;

public class BinarySearchTrace {
	/* 이진 탐색
	 *정렬된 배열에서 원하는 데이터를 찾는것이 이진 탐색
	 *이진 탐색의 반복은 루프 카운터를 사용하지 않고, 조건만 지정하므로 for가 아닌 while문을 사용한다.
	*/
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int[] a = {39, 41, 53, 55, 68, 72, 84, 88, 92, 97};
		int x, pos, left, right, middle;
		
		System.out.println("X를 입력해주세요.");
		x = scn.nextInt();
		pos = -1;
		left = 0;
		right = a.length -1;
		scn.close();
		
		System.out.printf("반복 실행 전: x = %d\n", x);
		System.out.printf("반복 실행 전: pos = %d, left = %d, middle = ?, right = %d\n", pos, left, right);
		
		while(pos == -1 && left <= right) {
			middle = (left + right) / 2;
			if(a[middle] == x) {
				pos = middle;
			} else if(a[middle] > x) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			
			System.out.printf("반복 실행 중: pos = %d, left = %d, middle = %d, right = %d\n", pos, left, middle, right);
		}
		
		System.out.printf("pos = %d\n", pos);
	}

}
