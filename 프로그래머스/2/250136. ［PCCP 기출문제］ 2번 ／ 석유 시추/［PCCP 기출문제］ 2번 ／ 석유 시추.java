import java.util.*;

class Solution {
    private static int n;
    private static int m;
    
    private static int[][] deltas = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    
    private static int[][] land;
    private static int[] oils;
    private static boolean[][] visited;
    
    private static void BFS(int r, int c) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {r, c});
        HashSet<Integer> set = new HashSet<>();
        visited[r][c] = true;
        int sum = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();
            int curR = cur[0];
            int curC = cur[1];
            
            set.add(curC);
            sum++;
            
            for (int[] delta : deltas) {
                int newR = curR + delta[0];
                int newC = curC + delta[1];
                
                if (newR < 0 || newC < 0 || newR >= n || newC >= m) continue;
                if (visited[newR][newC]) continue;
                if (land[newR][newC] == 0) continue;
                
                q.addLast(new int[] {newR, newC});
                visited[newR][newC] = true;
            }
        }
        
        for (int s : set) {
            oils[s] += sum;
        }
    }
    
    
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        this.land = land;
        oils = new int[m];
        visited = new boolean[n][m];
        
        for (int c = 0; c < m; c++) {
            for (int r = 0; r < n; r++) {
                if (visited[r][c] || land[r][c] == 0) continue;
                BFS(r, c);
            }
        }
        
        int answer = 0;
        
        for (int oil : oils) {
            answer = Math.max(answer, oil);
        }
        
        return answer;
    }
}