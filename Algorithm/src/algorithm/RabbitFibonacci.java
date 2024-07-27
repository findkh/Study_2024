package algorithm;

public class RabbitFibonacci {
	public static void main(String[] args) {
		int months = 6;
		int result = calculateRabbitPairs(months);
		System.out.println("6개월 후 토끼 쌍의 수: " + result);
	}

	public static int calculateRabbitPairs(int n) {
		if (n == 0 || n == 1) {
			return 1;
		}
		
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		return dp[n];
	}
}
