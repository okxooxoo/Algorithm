class Solution {
    int MAX_TIME = 24;
    
    public int solution(int[] players, int m, int k) {
        int count = 0; // 증설 횟수
        int[] servers = new int[MAX_TIME]; // 현재 증설된 서버 수
        
        for (int i = 0; i < MAX_TIME; i++) {
            // 현재 필요한 서버 수
            int required = players[i] / m;
            int realReq = required - servers[i];
            if (realReq > 0) {
                count += realReq;
                
                for (int j = i; j < i + k; j++) {
                    if (j >= MAX_TIME) break;
                    servers[j] += realReq;
                }
            }
        }
        
        return count;
    }
}