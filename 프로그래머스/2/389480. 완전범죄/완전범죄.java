import java.util.*;

class Solution {
    private static final int INF = Integer.MAX_VALUE;
    
    public int solution(int[][] info, int n, int m) {
        int len = info.length;
        int[][] dp = new int[len + 1][m];
        
        // 최댓값으로 초기화
        for (int i = 1; i <= len; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < m; j++) {
                int a = info[i - 1][0];
                int b = info[i - 1][1];
                
                // A가 훔쳤을 경우
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);
                // B가 훔쳤을 경우
                if (m > b + j) {
                    dp[i][b + j] = Math.min(dp[i][b + j], dp[i - 1][j]);
                }
            }
        }
        
        int answer = INF;
        for (int i = 0; i < m; i++) {
            answer = Math.min(answer, dp[len][i]);
        }
        
        if (answer >= n) return -1;
        return answer;
    }
}