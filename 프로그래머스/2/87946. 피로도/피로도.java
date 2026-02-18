class Solution {
    private static int answer;
    private static int n;
    private static int[][] dungeons;
    private static boolean[] visited;
    
    private static void DFS(int cnt, int tiredness) {
        answer = Math.max(answer, cnt);
        
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            if (tiredness < dungeons[i][0]) continue;
            
            visited[i] = true;
            DFS(cnt + 1, tiredness - dungeons[i][1]);
            visited[i] = false; // 백트래킹
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        n = dungeons.length;
        this.dungeons = dungeons;
        visited = new boolean[n];
        
        DFS(0, k);
        
        return answer;
    }
}