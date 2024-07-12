package algorithm;

public class InsertionSort {
	/*삽입 정렬(Insert Sort)
	 * 배열을 정렬하는 알고리즘
	 * */
	public static void main(String[] args) {
		int[] a = {90, 34, 78, 12, 56};
		
		System.out.print("정렬 전 배열: ");
		arrayToString(a);
		
		//오름 차순 정렬
		ascendingOrder(a);
		
		//내림 차순 정렬
		descendingOrder(a);
		
	}
	
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
	
	public static void ascendingOrder(int[] a) {
		int ins, cmp, temp;
		for(ins = 1; ins < a.length; ins++) {
			temp = a[ins];
			for(cmp = ins -1; cmp >= 0; cmp--) {
				if(a[cmp] > temp) {
					a[cmp + 1] = a[cmp];
				} else {
					break;
				}
			} a[cmp + 1] = temp;
		}
		System.out.print("오름 차순 정렬: ");
		arrayToString(a);
	}
	
	public static void descendingOrder(int[] a) {
		int ins, cmp, temp;
		for(ins = 1; ins < a.length; ins++) {
			temp = a[ins];
			for(cmp = ins -1; cmp >= 0; cmp--) {
				if(a[cmp] < temp) {
					a[cmp + 1] = a[cmp];
				} else {
					break;
				}
			} a[cmp + 1] = temp;
		}
		System.out.print("내림 차순 정렬: ");
		arrayToString(a);
	}
}
