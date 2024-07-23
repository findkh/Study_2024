package algorithm;

import java.util.Scanner;

/* 해시 테이블 탐색법
 * 해시 테이블이라는 자료 구조를 사용하여 검색하는 알고리즘
 * 데이터 저장에 배열 구조를 이용하며 다음과 같은 규칙을 정해둔다.
 * - 특정 값과 고유하게 연결할 키라는 데이터를 미리 준비한다.
 * - 키를 해시 함수라는 계산식에 넣어 얻은 결과(해시 값)를 배열의 인덱스로 사용한다.
 * - 키로 계산한 배열의 인덱스와 연결된 저장소에 키와 연결할 값을 저장한다.
 * */
public class HashTableSearch {
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
			
			//해시 테이블에 저장.
			hashTable[hashValue] = data;
		} while(true);
		
		do {
			System.out.println("탐색할 데이터 = ");
			data = scn.nextInt();
			
			//음수 값이 입력되면 데이터 탐색을 종료.
			if(data < 0) {
				break;
			}
			hashValue = hashFunc(data);
			
			//검색한 결과를 표시
			if(hashTable[hashValue] == data) {
				System.out.printf("%d번째에서 발견되었습니다. \n", hashValue);
			} else {
				System.out.println("찾을 수 없습니다.");
			}
		} while(true);
		scn.close();
	}
}
