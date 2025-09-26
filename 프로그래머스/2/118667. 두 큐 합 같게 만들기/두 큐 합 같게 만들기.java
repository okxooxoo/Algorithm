import java.util.*;

class Solution {
    ArrayDeque<Integer> aq = new ArrayDeque<>();
    ArrayDeque<Integer> bq = new ArrayDeque<>();
    long aSum = 0;
    long bSum = 0;
    
    public int solution(int[] queue1, int[] queue2) {
        for (int a : queue1) {
            aq.add(a);
            aSum += a;
        }
        
        for (int b : queue2) {
            bq.add(b);
            bSum += b;
        }
        
        if ((aSum + bSum) % 2 == 1) return -1;
        
        int answer = 0;
        
        while (!aq.isEmpty() || !bq.isEmpty()) {
            if (aSum == bSum) {
                return answer;
            } else if (aSum > bSum) {
                int temp = aq.pollFirst();
                bq.addLast(temp);
                
                aSum -= temp;
                bSum += temp;
            } else {
                int temp = bq.pollFirst();
                aq.addLast(temp);
                
                aSum += temp;
                bSum -= temp;
            }
            
            answer++;
            
            if (answer > (queue1.length + queue2.length) * 2) {
                return -1;
            }
        }
        
        return -1;
    }
}