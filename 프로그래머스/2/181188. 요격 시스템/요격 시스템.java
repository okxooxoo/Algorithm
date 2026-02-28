import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        // "미사일 끝나는 지점" 기준 오름차순 정렬
        Arrays.sort(targets, (a, b) -> a[1] - b[1]);
        
        int answer = 0;
        int curEnd = -1;
        
        for (int[] target : targets) {
            if (curEnd == -1) {
                answer++;
                curEnd = target[1];
                continue;
            }
            
            if (target[0] < curEnd) {
                continue;
            }
            
            answer++;
            curEnd = target[1];
        }
        
        return answer;
    }
}