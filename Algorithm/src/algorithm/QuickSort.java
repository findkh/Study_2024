package algorithm;

public class QuickSort {
/* 퀵 정렬(Quick Sort)
 * - 대량의 데이터를 효율적으로 정렬하는 알고리즘
 * 기준값이 되는 요소를 하나 선택해, 나머지 요소들을 기준값보다 작은 값과 큰 값으로 그룹 나누기를 반복하여 전체를 정렬한다.
 * 그룹 나누기 함수와 그 함수로 정렬을 수행하는 함수를 준비하면 퀵 정렬 프로그램 작성이 매우 간단해진다.
 * */
	
	public static void printArray(int[] a) {
		for(int i = 0; i < a.length; i++) {
			System.out.print("[" + a[i] + "]");
		}
		System.out.println();
	}
	
	//배열 a[head] ~ a[tail]을 그룹으로 나누는 메소드
	public static int divideArray(int[] a, int head, int tail) {
		int left, right, tmp;
		left = head + 1;
		right = tail;
		
		//기준값 a[head]보다 작은 요소를 앞쪽으로, 큰 요소를 뒤쪽으로 이동.
		while(true) {
			//배열을 첫 요소 +1부터 뒤쪽으로 훑어가며 기준값보다 큰 요소를 찾음.
			while(left < tail && a[head] > a[left]) {
				left++;
			}
			
			//배열 끝 요소에서 앞으로 훑어 기준값보다 작은 요소를 찾음
			while(a[head] < a[right]) {
				right--;
			}
			
			if(left >= right) {
				break;
			}
			
			//기준값보다 큰 a[left]와 기준값보다 작은 a[right]를 교환
			tmp = a[left];
			a[left] = a[right];
			a[right] = tmp;
			
			left++;
			right--;
		}
		
		//기준값 a[head]와 a[right]를 교환
		tmp = a[head];
		a[head] = a[right];
		a[right] = tmp;
		
		return right;
	}
	
	//오름차순으로 정렬하는 메소드
	public static void sortArray(int[] a, int start, int end) {
		int pivot;
		
		if(start < end) {
			//기준값과의 대소 관계에 따라 그룹 나
			pivot = divideArray(a, start, end);
			
			//기준값보다 작은 앞쪽 그룹에 동일한 처리를 적용 - 재귀 호출
			sortArray(a, start, pivot - 1);
			
			//기준값보다 큰 뒷쪽 그룹에 동일한 처리를 적용 - 재귀 호출
			sortArray(a, pivot + 1, end);
		}
	}
	
	public static void main(String[] args) {
		int[] a = {4, 7, 1, 6, 2, 5, 3};
		
		//정렬 전 배열
		printArray(a);
		
		//퀵 정렬 실행
		sortArray(a, 0, a.length - 1);
		
		//정렬된 배열을 표시
		printArray(a);
	}
}
