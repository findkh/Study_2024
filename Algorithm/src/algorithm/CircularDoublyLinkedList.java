package algorithm;

class StationList {
	/*양방향 원형 연결 리스트 (Circular Doubly Linked List)
	 * 구조: 양방향 연결 리스트와 마찬가지로 각 노드는 데이터와 함께 next와 prev 포인터를 포함한다.
	 * 첫 노드와 마지막 노드: 리스트의 마지막 노드는 첫 번째 노드를 가리키고, 첫 번째 노드는 마지막 노드를 가리키므로 리스트가 원형 구조를 형성한다.
	 * 연결 방식: 리스트의 처음과 끝이 서로 연결되어 있으며, 순회가 계속 이루어질 수 있다.
	 * 용도: 반복적인 순회가 필요한 경우에 사용되며, 한 방향으로 끝없이 순회할 수 있는 구조가 필요할 때 유용하다.
	 * */
	
	public String name;
	public int next;
	public int prev;
	
	public StationList() {
		this.name = null;
		this.next = -1;
		this.prev = -1;
	}
}

public class CircularDoublyLinkedList {
	public static StationList[] list = new StationList[10];
	public static int head;
	
	public static void initStationList() {
		for (int i = 0; i < list.length; i++) {
			list[i] = new StationList();
		}
		
		list[0].name = "부산";
		list[1].name = "대전";
		list[2].name = "서울";
		list[3].name = "동대구";
		list[4].name = "천안아산";
		
		// 초기화 작업: 원형 연결 리스트 설정
		list[2].next = 4;  // 서울 -> 천안아산
		list[2].prev = 0;  // 서울 <- 부산
		
		list[4].next = 1;  // 천안아산 -> 대전
		list[4].prev = 2;  // 천안아산 <- 서울
		
		list[1].next = 3;  // 대전 -> 동대구
		list[1].prev = 4;  // 대전 <- 천안아산
		
		list[3].next = 0;  // 동대구 -> 부산
		list[3].prev = 1;  // 동대구 <- 대전
		
		list[0].next = 2;  // 부산 -> 서울
		list[0].prev = 3;  // 부산 <- 동대구
		
		
		head = 2; // 서울을 head로 설정
	}
	
	public static void printStationList() {
		int idx = head;
		if (idx == -1) return;
		
		do {
			System.out.printf("[" + list[idx].name + "] -> ");
			idx = list[idx].next;
		} while (idx != head);
			System.out.println();
		}
	
	public static void printReverseStationList() {
		int idx = head;
		if (idx == -1) return;
		
		// 끝 노드 찾기
		while (list[idx].next != head) {
			idx = list[idx].next;
		}

		// 끝 노드부터 head까지 출력
		int lastIdx = idx;
		do {
			System.out.printf("[" + list[idx].name + "] -> ");
			idx = list[idx].prev;
		} while (idx != lastIdx);
		System.out.println();
	}
	
	public static void insertStationList(int insIdx, String insName, int prevIdx) {
		list[insIdx].name = insName;
		list[insIdx].next = list[prevIdx].next;
		list[insIdx].prev = prevIdx;
		
		list[list[prevIdx].next].prev = insIdx;
		list[prevIdx].next = insIdx;
	}
	
	public static void deleteStationList(int delIdx) {
		list[list[delIdx].prev].next = list[delIdx].next;
		list[list[delIdx].next].prev = list[delIdx].prev;
	}
	
	public static void main(String[] args) {
		initStationList();
		printStationList();
		printReverseStationList();
		
		insertStationList(5, "광명", 2);
		printStationList();
		printReverseStationList();
		
		deleteStationList(5);
		printStationList();
		printReverseStationList();
	}
}
