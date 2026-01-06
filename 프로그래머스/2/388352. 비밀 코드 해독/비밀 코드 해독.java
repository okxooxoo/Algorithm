import java.util.*;

class Solution {
    private static int LEN = 5;
    
    private static int n;
    private static int[][] q;
    private static int[] ans;
    private static int[] candidate; // 비밀 코드 후보
    private static int tryCount; // 시도 횟수
    
    private static int answer;
    
    private static boolean isValidCode() {
        // 비밀 코드 후보를 Set으로 변환
        Set<Integer> set = new HashSet<>();
        for (int n : candidate) {
            set.add(n);
        }
        
        for (int i = 0; i < tryCount; i++) {
            int count = 0;
            
            for (int j = 0; j < 5; j++) {
                if (set.contains(q[i][j])) {
                    count++;
                }
            }
            
            if (count != ans[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    // 가능한 모든 정수 조합 찾기
    private static void makeComb(int depth, int index) {
        if (depth >= 5) {
            if (isValidCode()) {
                answer++;
            }
            return;
        }
        
        for (int i = index; i <= n; i++) {
            candidate[depth] = i;
            makeComb(depth + 1, i + 1);
        }
    }
    
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        
        this.candidate = new int[5];
        this.tryCount = q.length;
        this.answer = 0;
        
        makeComb(0, 1);
        
        return answer;
    }
}