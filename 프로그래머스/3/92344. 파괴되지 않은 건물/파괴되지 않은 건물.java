class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] preSum = new int[n + 1][m + 1];
        
        for (int[] sk : skill) {
            int type = sk[0];
            int r1 = sk[1];
            int c1 = sk[2];
            int r2 = sk[3];
            int c2 = sk[4];
            int degree = type == 1 ? -sk[5] : sk[5];
            
            preSum[r1][c1] += degree;
            preSum[r2 + 1][c1] -= degree;
            preSum[r1][c2 + 1] -= degree;
            preSum[r2 + 1][c2 + 1] += degree;
        }
        
        // 누적합 구하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                preSum[i][j + 1] += preSum[i][j];
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                preSum[i + 1][j] += preSum[i][j];
            }
        }
        
        int answer = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                preSum[i][j] += board[i][j];
                if (preSum[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}