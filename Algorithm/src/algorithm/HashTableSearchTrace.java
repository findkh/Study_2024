package algorithm;

import java.util.Scanner;


public class HashTableSearchTrace {
	public static int[] hashTable = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,};
	
	//해시 함수 역할을 하는 메소드
	public static int hashFunc(int data) {
		return data % 10;
	}
	
	public static void main(String[] args) {
		int data, hashValue;
		Scanner scn = new Scanner(System.in);
		do {
			System.out.println("저장할 데이터 = ");
			data = scn.nextInt();
			
			if(data < 0) {
				break;
			}
			
			//해시값을 구함.
			hashValue = hashFunc(data);
			System.out.printf("해시값 = %d %% 10 = %d\n", data, hashValue);
			
			//해시 테이블에 저장.
			hashTable[hashValue] = data;
			System.out.printf("hashTable[%d]에 %d를 저장합니다.\n", hashValue, data);
		} while(true);
		
		do {
			System.out.println("탐색할 데이터 = ");
			data = scn.nextInt();
			
			//음수 값이 입력되면 데이터 탐색을 종료.
			if(data < 0) {
				break;
			}
			hashValue = hashFunc(data);
			System.out.printf("해시값 = %d %% 10 =%d\n", data, hashValue);
			
			//검색한 결과를 표시
			if(hashTable[hashValue] == data) {
				System.out.printf("hashTable[%d]값은 %d이므로, 발견된 위치를 표시합니다.\n", hashValue, data);
				System.out.printf("%d번째에서 발견되었습니다. \n", hashValue);
			} else {
				System.out.printf("hashTable[%d값은 %d이(가) 아니므로, '찾을 수 없습니다'를 표시합니다. \n", hashValue, data);
				System.out.println("찾을 수 없습니다.");
			}
		} while(true);
		scn.close();
	}
}
