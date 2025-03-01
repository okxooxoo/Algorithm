import java.util.*;

class Solution {
    private static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    private static int R;
    private static int C;
    
    private static String[][] matrix;
    private static boolean[][] isEmpty; // 컨테이너가 꺼내졌는지 여부
    
    // 해당 문자열의 컨테이너를 모두 꺼내기
    private static void crane(String alphabet) {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (matrix[r][c].equals(alphabet)) {
                    isEmpty[r][c] = true;
                }
            }
        }
    }
    
    // 해당 문자열의 컨테이너를 "바깥에 노출된 것만" 꺼내기
    private static void forklift(String alphabet) {
        ArrayList<int[]> list = new ArrayList<>();
        
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (matrix[r][c].equals(alphabet)) {
                    int[] coord = BFS(r, c);
                    
                    if (coord[0] != -1) {
                        list.add(coord);
                    }
                }
            }
        }
        
        // 컨테이너 일괄 꺼내기
        for (int[] coord : list) {
            int r = coord[0];
            int c = coord[1];
            
            isEmpty[r][c] = true;
        }
    }
    
    private static boolean isInvalidCoord(int r, int c) {
        return (r < 0 || c < 0 || r >= R || c >= C);
    }
    
    private static int[] BFS(int r, int c) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];
        dq.offer(new int[] {r, c});
        
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int curR = cur[0];
            int curC = cur[1];
            
            if (visited[curR][curC]) {
                continue;
            }
            
            visited[curR][curC] = true;
            
            for (int[] delta : deltas) {
                int nextR = curR + delta[0];
                int nextC = curC + delta[1];
                
                if (isInvalidCoord(nextR, nextC)) {
                    return new int[] {r, c};
                }
                
                if (isEmpty[nextR][nextC]) {
                    dq.addLast(new int[] {nextR, nextC});
                }
            }
        }
        
        return new int[] {-1, -1};
    }
    
    // 남은 컨테이너의 수 반환
    private static int getContainerCount() {
        int count = 0;
        
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!isEmpty[r][c]) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public int solution(String[] storage, String[] requests) {
        // 초기화
        R = storage.length;
        C = storage[0].length();
        
        matrix = new String[R][C];
        
        for (int r = 0; r < R; r++) {
            matrix[r] = storage[r].split("");
        }
        
        isEmpty = new boolean[R][C];
        
        // 출고 요청 실행
        for (String request : requests) {
            if (request.length() == 1) {
                // 지게차
                forklift(request.split("")[0]);
            } else {
                // 크레인
                crane(request.split("")[0]);
            }
        }
        
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (isEmpty[r][c]) {
                    System.out.print("0 ");
                } else {
                    System.out.print("1 ");
                }
            }
            System.out.println();
        }
        
        int answer = getContainerCount();
        return answer;
    }
}