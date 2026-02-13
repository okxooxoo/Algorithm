import java.util.*;

class Solution {
    private static int[][] deltas = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    
    private static int n; // 행 길이
    private static int m; // 열 길이
    
    private static int solve(int[][] maps) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        dq.offer(new int[] {0, 0, 1});
        
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int r = cur[0];
            int c = cur[1];
            int d = cur[2];
            
            // 종료 조건
            if (r == n - 1 && c == m - 1) {
                return d;
            }
            
            // 범위를 벗어나면
            if (r < 0 || c < 0 || r >= n || c >= m) {
                continue;
            }
            
            // 벽이 있으면
            if (maps[r][c] == 0) {
                continue;
            }
            
            // 이미 방문했으면
            if (visited[r][c]) {
                continue;
            }
            
            visited[r][c] = true;
            
            for (int[] delta : deltas) {
                int nr = r + delta[0];
                int nc = c + delta[1];
                dq.addLast(new int[] {nr, nc, d + 1});
            }
        }
    
        return -1;
    }
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        
        int answer = solve(maps);
        return answer;
    }
}