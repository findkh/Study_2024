package algorithm;

import java.util.Scanner;

public class LinearSearch {
	public static void main(String[] args) {
		/*
		 * 선형 검색(LinearSearch)
		 * -> 임의의 배열에서 원하는 데이터를 찾는 알고리즘
		 * 배열에서 찾는 값이 여러개 있어도 처음 발견한 시점에 종료한다.
		 * pos:발견된 위치, -1로 정해두고 없으면 -1을 리턴한다.
		*/
		
		Scanner scn = new Scanner(System.in);
		int x = scn.nextInt();
		scn.close();
		System.out.printf("입력값: %d\n", x);
		int[] a = {72, 68, 92, 88, 41, 53, 97, 84, 39, 55};
		int pos = -1;
		
		
		for(int i = 0; i < a.length && pos == -1; i++) {
			System.out.printf("i값: %d \n", i);
			if(a[i] == x) {
				pos = i;
			}
		}
		
		System.out.printf("위치: %d\n", pos);
	}
}
