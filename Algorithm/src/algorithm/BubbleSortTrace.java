package algorithm;

public class BubbleSortTrace {
	/* 버블 정렬
	 * 서로 인접한 두 원소를 검사하여 정렬하는 알고리즘
	 * */
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
		int[] a = {90, 34, 78, 12, 56};
		int ins, cmp, temp;
		
		System.out.println("정렬 전 배열");
		arrayToString(a);
		
		for(ins = 0; ins <a.length-1; ins++) {
			System.out.printf("내부 반복문: %d회\n", ins + 1);
			for(cmp = 0; cmp < a.length - ins - 1; cmp++) {
				if(a[cmp] >  a[cmp + 1]) {
					temp = a[cmp];
					a[cmp] = a[cmp + 1];
					a[cmp + 1] = temp;
				}
				System.out.printf("내부 반복문: ins = d, cmp = %d, a[%d] = %d\n", ins, cmp, cmp, a[cmp]);
				arrayToString(a);
			}
			System.out.printf("외부 반복문: ins = d, cmp = %d, a[%d] = %d\n", ins, cmp, cmp, a[cmp]);
			System.out.printf("외부 반복문: 확정된 정렬 위치 = a[%d] <- %d\n", cmp, a[cmp]);
		}
		
		System.out.println("정렬 후 배열");
		arrayToString(a);
	}
}
