package algorithm;

public class KnapsackDP {
	//배낭의 최대 무게
	public static final int KNAP_MAX = 6;
	
	//물건의 종류
	public static final int ITEM_NUM = 5;
	
	//물건의 명칭
	public static char[] name = {'A', 'B', 'C', 'D', 'E'};
	
	//물건의 무게
	public static int[] weight = {1, 2, 3, 4, 5};
	
	//물건의 가치
	public static int[] value = {100, 300, 350, 500, 650};
	
	//물건을 넣을지 판단한 직후의 최대 가치
	public static int[][] maxValue = new int[ITEM_NUM][KNAP_MAX + 1];
	
	public static int[] lastItem = new int[KNAP_MAX + 1];
	
	public static void showKnap(int item) {
		int knap;
		
		System.out.printf("<%c, %dkg, %d원을 고려한 결과>\n", name[item], weight[item], value[item]);
		
		//각 배낭의 무게를 표시
		for(knap = 0; knap <= KNAP_MAX; knap++) {
			System.out.printf("%dkg\t", knap);
		}
		
		System.out.println();
		
		//배낭에 담긴 상품 가치의 합계를 표시
		for(knap = 0; knap <= KNAP_MAX; knap++) {
			System.out.printf("%d원\t", maxValue[item][knap]);
		}
		
		System.out.println();
		
		//배낭에 마지막으로 넣은 물건을 표시
		for(knap = 0; knap <= KNAP_MAX; knap++) {
			if(lastItem[knap] != -1) {
				System.out.printf("%c\t", name[lastItem[knap]]);
			} else {
				System.out.printf("없음\t");
			}
		}
		System.out.printf("\n\n");
	}
	
	public static void main(String[] args) {
		int item;
		int knap;
		int selVal;
		int totalWeight;
		
		item = 0;
		for(knap = 0; knap <= KNAP_MAX; knap++) {
			//최대 무게 이하면 선택
			if(weight[item] <= knap) {
				maxValue[item][knap] = value[item];
				lastItem[knap] = item;
			} else {
				//최대 무게 이하가 아니면 선택하지 않음
				maxValue[0][knap] = 0;
				lastItem[knap] = -1;
			}
		}
		
		showKnap(item);
		
		//물건 고려
		for(item = 1; item < ITEM_NUM; item++) {
			
			//배낭 고려
			for(knap = 0; knap <= KNAP_MAX; knap++) {
				if(weight[item] <= knap) { //최대 무게 이하인 경우
					//선택한 가치를 구함
					selVal = maxValue[item - 1][knap - weight[item]] + value[item];
					
					//가치가 크면 선택
					if(selVal > maxValue[item - 1][knap]) {
						maxValue[item][knap] = selVal;
						lastItem[knap] = item;
					} else {
						//가치가 크지 않으면 선택하지 않음.
						maxValue[item][knap] = maxValue[item - 1][knap];
					}
				} else { //최대 무게 이하가 아니면 선택하지 않음
					maxValue[item][knap] = maxValue[item - 1][knap];
				}
			}
			showKnap(item);
		}
		
		//정답 출력
		System.out.println("<배낭에 들어 있는 물건>");
		totalWeight = 0;
		for(knap = KNAP_MAX; knap > 0; knap -= weight[item]) {
			item = lastItem[knap];
			System.out.printf("%dkg의 배낭에 마지막으로 넣은 물건은 %c입니다.\n", knap, name[item]);
			totalWeight += weight[item];
			System.out.printf("%c, %dkg, %d원\n", name[item], weight[item], value[item]);
			System.out.printf("%dkg - %dkg = %dkg 입니다.\n", knap, weight[item], knap - weight[item]);
		}
		
		System.out.println("<정답표시>");
		System.out.printf("무게의 합계 = %dkg\n", totalWeight);
		System.out.printf("가치의 최댓값 = %d원\n", maxValue[ITEM_NUM - 1][KNAP_MAX]);
	}
	
	
}
