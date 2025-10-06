import java.util.*;

class Solution {
    private static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    private static boolean BFS(String[] place, int mr, int mc) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {mr, mc, 0});
        boolean[][] visited = new boolean[5][5];
        
        while(!dq.isEmpty()) {
            int[] person = dq.pollFirst();
            int r = person[0];
            int c = person[1];
            int d = person[2];
            
            // 거리 2를 넘으면
            if (d > 2) {
                continue;
            }
            
            // 대기실 범위를 벗어나면
            if (r < 0 || c < 0 || r >= 5 || c >= 5) {
                continue;
            }
            
            // 이미 방문했으면
            if (visited[r][c]) {
                continue;
            }
            
            visited[r][c] = true; // 방문 체크
            
            // 다른 사람이 맨해튼 거리 2 이내에 존재하면
            if (d > 0 && place[r].charAt(c) == 'P') {
                return false;
            }
            
            // 파티션을 만나면
            if (place[r].charAt(c) == 'X') {
                continue;
            }
            
            for (int[] delta : deltas) {
                int nr = r + delta[0];
                int nc = c + delta[1];
                dq.addLast(new int[] {nr, nc, d + 1});
            }
        }
        
        return true;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int idx = 0;
        
        for (String[] place : places) {
            boolean flag = true;
            
            Loop : for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (place[i].charAt(j) == 'P') {
                        if (!BFS(place, i, j)) {
                            flag = false;
                            break Loop;
                        }
                    }
                }
            }
            
            answer[idx++] = flag ? 1 : 0;
        }
        
        return answer;
    }
}