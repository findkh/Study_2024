package algorithm;

/*
 * 트리: 나무의 가지가 분기하여 뻗어가는 구조를 컴퓨터의 자료구조로 구현한 것.
 * 노드(node): 트리를 구성하는 원을 노드(=마디)라고 한다.
 * 루트(root): 트리 맨 위에 있는 노드를 루트(=뿌리)라고 한다.
 * 부모노드(parent node): 아래 노드가 있을 경우.
 * 자식노드(child node): 위에 노드가 있을 경우.
 * 형제 노드(siblings node): 같은 부모를 갖는 노드.
 * 서브 트리(sub tree): 부모와 자식 노드 형태로 묶인 트리의 일부분.
 * 리프 노드(leaf node): 트리의 맨 아래에 있고, 별도의 자식 노드가 없는 경우.
 * 노드의 길이: 어떤 노드에서 다른 노드로 이동할 때 거치는 노드의 개수.
 * 노드의 경로: 어떤 노드에서 다른 노드로 이동할 때 거치는 노드의 순서.
 * 노드의 크기: 자기 자신과 자식 노드를 포함한 노드의 개수.
 * 노드의 차수: 노드별 자식 노드의 개수.
 * 트리의 높이: 루트 노드부터 리프 노드까지 도달하는 경우 중 가장 긴 경로의 길이.
 * 트리의 차수: 노드의 차수 중 가장 높은 숫자의 차수.
 * 
 * 이진 탐색 트리(Binary Search Tree)
 * - 가장 기본적인 트리 구조의 하나로 연결 리스트를 응용한 것.
 * */
class BST{
	public int data;
	public int left;
	public int right;
}

public class BinarySearchTree {
	public static BST[] tree = new BST[10];
	public static int rootIdx = 0;
	public static int newIdx = 0;
	
	public static void addBST(int data) {
		int currentIdx;
		boolean addFlag;
		
		tree[newIdx].data = data;
		tree[newIdx].left = -1;
		tree[newIdx].right = -1;
		
		if(newIdx != rootIdx) {
			currentIdx = rootIdx; //루트부터 이진 탐색 트리를 내려감.
			addFlag = false;
			do {
				if(data < tree[currentIdx].data) {
					if(tree[currentIdx].left == -1) { //왼쪽으로 내려갔을 때 끝이면 left 요소의 물리적 위치 인덱스를 추가(연결 정보 설정)
						tree[currentIdx].left = newIdx;
						addFlag = true;
					} else { //왼쪽으로 내려갔을 때 끝이 아니라면 왼쪽의 요소보다 더 내려감.
						currentIdx = tree[currentIdx].left;
					}
				} else { //루트 요솟값보다 더 크면 오른쪽으로 내려감.
					if(tree[currentIdx].right == -1) {
						tree[currentIdx].right = newIdx;
						addFlag = true;
					} else { //오른쪽으로 내려갔을 때 끝이 아니라면 오른쪽의 요소보다 더 내려감.
						currentIdx = tree[currentIdx].right;
					}
				}
			} while(addFlag == false);
		}
		// 다음에 추가할 요소를 위해 물리적 위치의 인덱스를 1늘림.
		newIdx++;
	}
	
	//이진 탐색 트리의 실체인 배열을 물리적 위치 순서로 표시하는 메서드
	public static void printPhysicalBST() {
		for(int i = 0; i < newIdx; i++) {
			System.out.printf("tree[%d]: data = %d, left = %d, right = %d\n", i, tree[i].data, tree[i].left, tree[i].right);
		}
	}
	
	//이진 탐색 트리를 논리적인 순서로 표시하는 메소드.
	public static void printLogicalBST(int currentIdx) {
		if(currentIdx != -1) {
			System.out.printf("tree[%d]: data = %d, left = %d, right = %d\n", currentIdx, tree[currentIdx].data, tree[currentIdx].left, tree[currentIdx].right);
			
			//재귀호출
			printLogicalBST(tree[currentIdx].left);
			printLogicalBST(tree[currentIdx].right);
		}
	}
	
	//이진 탐색 트리를 탐색하는 메소드
	public static int searchBST(int x) {
		int idx;
		int currentIdx;
		idx = -1;
		currentIdx = rootIdx;
		
		while(currentIdx != -1) {
			if(tree[currentIdx].data == x) {
				idx = currentIdx;
				break;
			} else if(tree[currentIdx].data > x) {
				currentIdx = tree[currentIdx].left;
			} else {
				currentIdx = tree[currentIdx].right;
			}
		}
		return idx;
	}
	
	//재귀호출로 이진탐색트리를 탐색하는 함수
	public static int searchRecBST(int x, int currentIdx) {
		if(currentIdx == -1) {
			return -1;
		} else {
			if(tree[currentIdx].data == x) {
				return currentIdx;
			} else if(tree[currentIdx].data > x) {
				//재귀 호출 부분
				return searchRecBST(x, tree[currentIdx].left);
			} else {
				return searchRecBST(x, tree[currentIdx].right);
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < tree.length; i++) {
			tree[i] = new BST();
		}
		
		addBST(4);
		addBST(6);
		addBST(5);
		addBST(2);
		addBST(3);
		addBST(7);
		addBST(1);
		
		//물리적 위치 출력
		printPhysicalBST();
		
		System.out.println("====================================");
		
		//이진 탐색 트리를 논리적인 순서로 표시
		printLogicalBST(rootIdx);
		
		System.out.println("====================================");
		
		//이진 탐색 트리를 탐색
		System.out.printf("data값이 5일 때 물리적 위치 탐색 결과 = tree[%d]\n", searchBST(5));
		System.out.printf("data값이 8일 때 물리적 위치 탐색 결과 = tree[%d]\n", searchBST(8));
		
		System.out.println("====================================");
		
		//재귀 호출로 이진 탐색 트리를 탐색
		System.out.printf("data값이 5일 때 물리적 위치 탐색 결과 = tree[%d]\n", searchRecBST(5, rootIdx));
		System.out.printf("data값이 8일 때 물리적 위치 탐색 결과 = tree[%d]\n", searchRecBST(8, rootIdx));
		
	}
}
