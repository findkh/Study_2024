package algorithm;

public class SelectionSortTrace {
	/* 선택 정렬(Selection Sort)
	 * 입력 배열(정렬되지 않은 값들) 이외에 다른 추가 메모리를 요구하지 않는 정렬 방법
	 *  */
	public static void arrayToString(int[] a) {
		System.out.print("[");
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			if (i < a.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}
	
	public static void main(String[] args) {
		System.out.println("선택 정렬");
		int[] a = {90, 34, 78, 12, 56};
		int ins, cmp, min, temp;
	
		System.out.println("정렬 전 배열: ");
		arrayToString(a);
		
		for(ins = 0; ins < a.length -1; ins++) {
			System.out.printf("외부 반복문: %d회\n", ins + 1);
			min = ins;
			for(cmp = ins + 1; cmp <a.length; cmp++) {
				if(a[cmp] < a[ins]) {
					min = cmp;
					temp = a[ins];
					a[ins] = a[min];
					a[min] = temp;
					System.out.printf("배열 요소의 현재 최솟값: a[%d] = %d\n", ins, a[ins]);
					arrayToString(a);
				}
				System.out.printf("내부 반복문: ins = %d, cmp = %d, a[%d] = %d\n", ins, cmp, cmp, a[cmp]);
				arrayToString(a);
			}
			System.out.printf("외부 반복문: ins = %d, cmp = %d, a[%d] = %d\n", ins, cmp, ins, a[ins]);
			System.out.printf("외부 반복문: 확정된 정렬 위치 = a[%d] <- %d\n", ins, a[ins]);
			arrayToString(a);
		}
		System.out.printf("외부 반복문: 확정된 정렬 위치 = a[%d] <- %d\n", ins, a[ins]);
		System.out.println("정렬 후 배열: ");
		arrayToString(a);
	}
}
