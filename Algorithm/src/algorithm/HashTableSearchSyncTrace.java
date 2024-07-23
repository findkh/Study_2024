package algorithm;

import java.util.Scanner;

/* 해시 충돌 대처
 * 해시 테이블 탐색법은 이상적인 시간 복잡도가 O(1)이다.
 * 해시 충돌(해시값이 동일한 데이터가 생긴 상황)이 발생한 경우 처리 한 번으로 찾을 수 없다.
 * 해시 테이블 탐색법은 동의어에 대처하는 해시 충돌 처리 방법을 고려해야 한다.
 * */
public class HashTableSearchSyncTrace {
	public static int[] hashTable = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,};
	
	//해시 함수 역할을 하는 메소드
	public static int hashFunc(int data) {
		return data % 10;
	}
	
	public static void main(String[] args) {
		int data, hashValue;
		int pos; // 저장 위치, 검색 위치
		
		Scanner scn = new Scanner(System.in);
		do {
			System.out.print("저장할 데이터 = ");
			data = scn.nextInt();
			
			if (data < 0) {
				break;
			}
			
			//해시값을 구함.
			hashValue = hashFunc(data);
			System.out.printf("해시값 = %d %% 10 = %d\n", data, hashValue);
			
			//데이터의 저장 위치를 정함.
			pos = hashValue;
			System.out.printf("저장 위치 pos = %d\n", hashValue);
			while (hashTable[pos] != -1) {
				// 배열 요소에서 데이터를 저장할 수 있는지 확인
				pos++;
				
				//배열의 마지막 요소까지 데이터 저장 가능 여부를 확인하면 배열 첫번째 요소를 지정.
				if (pos >= hashTable.length) {
					pos = 0;
				}
				System.out.printf("저장 위치 pos = %d\n", pos);
				
				//해시값의 배열 요소 위치까지만 돌아오면, 해시 테이블에 데이터가 가득 찬 것이므로 반복을 종료.
				if (pos == hashValue) {
					break;
				}
			}
			
			if (hashTable[pos] == -1) {
				//해시 테이블 데이터가 가득 차지 않았다면 데이터를 저장.
				hashTable[pos] = data;
				System.out.printf("hashTable[%d]에 %d를 저장합니다.\n", pos, data);
			} else {
				System.out.println("해시 테이블이 가득 찼습니다.");
			}
		} while (true);
		
		//해시 테이블에서 데이터 탐색.
		do {
			System.out.print("탐색할 데이터 = ");
			data = scn.nextInt();
			
			//음수 값이 입력되면 데이터 탐색을 종료.
			if (data < 0) {
				break;
			}
			
			hashValue = hashFunc(data);
			System.out.printf("해시값 = %d %% 10 = %d\n", data, hashValue);
			
			pos = hashValue;
			System.out.printf("탐색 위치 pos = %d\n", pos);
			while (hashTable[pos] != -1 && hashTable[pos] != data) {
				pos++;
				
				if (pos >= hashTable.length) {
					pos = 0;
				}
				System.out.printf("탐색 위치 pos = ", pos);
				
				if (pos == hashValue) {
					break;
				}
			}
			
			//검색한 결과를 표시
			if (hashTable[pos] == data) {
				System.out.printf("hashTable[%d]값은 %d이므로, 발견한 위치를 표시힙니다.\n", pos, data);
				System.out.printf("%d번째에서 발견되었습니다. \n", pos);
			} else {
				System.out.printf("hashTable[%d]값은 %d이므로, '찾을 수 없습니다.'를 표시합니다.\n", pos, hashTable[pos]);
				System.out.println("찾을 수 없습니다.");
			}
		} while (true);
		scn.close();
	}
}
