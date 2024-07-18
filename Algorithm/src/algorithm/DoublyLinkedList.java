package algorithm;

class StationList {
	/*양방향 연결 리스트 (Doubly Linked List)
	 * 구조: 각 노드는 데이터와 함께 다음 노드를 가리키는 포인터 (next)와 이전 노드를 가리키는 포인터 (prev)를 포함한다.
	 * 첫 노드와 마지막 노드: 첫 번째 노드의 prev 포인터는 null 또는 특별한 값으로 설정되어 있으며, 마지막 노드의 next 포인터도 null 또는 특별한 값으로 설정되어 있다.
	 * 연결 방식: 노드들이 일직선으로 연결되어 있으며, 처음과 끝이 연결되어 있지 않다.
	 * 용도: 일반적으로 삽입과 삭제가 빈번히 일어나는 경우에 사용되며, 양방향 순회가 필요할 때 유용하다.
	 * */
	
	public String name;
	public int next;
	public int prev;  // 이전 노드를 가리키는 필드 추가

	public StationList() {
		this.name = null;
		this.next = -1;
		this.prev = -1;  // 초기값 설정
	}
}

public class DoublyLinkedList {
	public static StationList[] list = new StationList[10];
	public static int head;
	
	public static void initStationList() {
		for (int i = 0; i < list.length; i++) {
			list[i] = new StationList();
		}
		
		list[0].name = "부산";
		list[0].next = -1;
		list[0].prev = 3;  // 초기값 설정
		
		list[1].name = "대전";
		list[1].next = 3;
		list[1].prev = 4;  // 초기값 설정
		
		list[2].name = "서울";
		list[2].next = 4;
		list[2].prev = -1;  // 초기값 설정
		
		list[3].name = "동대구";
		list[3].next = 0;
		list[3].prev = 1;  // 초기값 설정
		
		list[4].name = "천안아산";
		list[4].next = 1;
		list[4].prev = 2;  // 초기값 설정
		
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

	public static void printReverseStationList() {
		// 끝 노드를 찾는다.
		int idx = head;
		while (list[idx].next != -1) {
			idx = list[idx].next;
		}
		
		// 끝 노드부터 head까지 출력
		while (idx != -1) {
			System.out.printf("[" + list[idx].name + "] -> ");
			idx = list[idx].prev;
		}
		System.out.println();
	}
	
	public static void insertStationList(int insIdx, String insName, int prevIdx) {
		list[insIdx].name = insName;
		list[insIdx].next = list[prevIdx].next;
		list[insIdx].prev = prevIdx;
		
		if (list[prevIdx].next != -1) {
			list[list[prevIdx].next].prev = insIdx;
		}
		list[prevIdx].next = insIdx;
	}
	
	public static void deleteStationList(int delIdx, int prevIdx) {
		list[prevIdx].next = list[delIdx].next;
		if (list[delIdx].next != -1) {
			list[list[delIdx].next].prev = prevIdx;
		}
	}

	public static void main(String[] args) {
		initStationList();
		printStationList();
		printReverseStationList();
		
		insertStationList(5, "광명", 2);
		printStationList();
		printReverseStationList();
		
		deleteStationList(5, 2);
		printStationList();
		printReverseStationList();
	}
}
