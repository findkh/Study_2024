package algorithm;

class StationList {
	public String name;
	public int next;
}

public class LinkedList {
	/* 연결 리스트(LinkedList)
	 * 구조체(structure): 여러 데이터를 하나로 묶은 것
	 * 구조체를 활용하여 연결 리스트를 구현할 수 있다.
	 * 연결리스트는 배열의 한 요소가 어디에 연결되어 있는지에 관한 정보를 갖고 있다.
	 * 자바에서는 참조(reference)라는 개념으로 구현한다.
	 * 연결리스트를 사용하면 요소의 물리적 순서와 관계없이 연결 정보로 요소의 논리적 순서를 결정한다.
	 * 장점: 일반 배열과 비교하면 요소의 삽입과 제거가 효율적이다.
	 * 단점: 일반 배열과 비교하면 논리적인 순서의 "몇번째"라는 위치를 지정해 요소를 읽을 때 많은 처리가 필요하다.
	 * */
	
	public static StationList[] list = new StationList[10];
	
	public static int head;
	
	//연결 리스트의 초기 상태를 만드는 함수
	public static void initStationList() {
		for(int i = 0; i < list.length; i++) {
			list[i] = new StationList();
		}
		
		list[0].name = "부산";
		list[0].next = -1;
		list[1].name = "대전";
		list[1].next = 3;
		list[2].name = "서울";
		list[2].next = 4;
		list[3].name = "동대구";
		list[3].next = 0;
		list[4].name = "천안아산";
		list[4].next = 1;
		
		head = 2;
	}
	
	public static void printStationList() {
		int idx = head;
		while (idx != -1) {
			System.out.printf("[" + list[idx].name + "] -> ");
			idx = list[idx].next;
		}
		System.out.println();
	}
	
	public static void insertStationList(int insIdx, String insName, int prevIdx) {
		list[insIdx].name = insName;
		list[insIdx].next = list[prevIdx].next;
		list[prevIdx].next = insIdx;
	}
	
	public static void deleteStationList(int delIdx, int prevIdx) {
		list[prevIdx].next = list[delIdx].next;
	}
	
	public static void main(String[] args) {
		initStationList();
		printStationList();
		
		insertStationList(5, "광명", 2);
		printStationList();
		
		deleteStationList(5, 2);
		printStationList();
	}
}

