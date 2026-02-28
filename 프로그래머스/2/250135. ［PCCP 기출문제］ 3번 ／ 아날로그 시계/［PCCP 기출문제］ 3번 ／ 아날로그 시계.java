class Solution {
    // 00:00부터 time까지 알람이 몇 번 울리는지 계산
    private static int calc(int time) {
        // 분침과 겹치는 경우
        int m = time * 59 / 3600;
        
        // 시침과 겹치는 경우
        int h = time * 719 / 43200;
        
        // 시침/분침/초침이 모두 겹치는 경우 빼주기(알람은 한번만 울림)
        int dup = 43200 <= time ? 2 : 1;
        
        return m + h - dup;
    }
    
    private static boolean alarmNow(int time) {
        return time * 59 % 3600 == 0 || time * 719 % 43200 == 0;
    }
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) { 
        int start = 3600 * h1 + 60 * m1 + s1;
        int end = 3600 * h2 + 60 * m2 + s2;
        
        int answer = calc(end) - calc(start) + (alarmNow(start) ? 1 : 0);
            
        return answer;
    }
}