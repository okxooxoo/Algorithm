import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[][] map;
    private static int[][] deltas = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    private static int minH;
    private static int maxH;
    private static int answer;

    private static void solution() {
        for (int h = minH; h <= maxH; h++) {
            // 잠긴 영역 표시
            boolean[][] newMap = new boolean[N][N];
            int count = 0; // 영역의 수

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= h) {
                        newMap[i][j] = true;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!newMap[i][j]) {
                        BFS(newMap, i, j);
                        count++;
                    }
                }
            }

            answer = Math.max(answer, count);
        }
    }

    private static void BFS(boolean[][] map, int r, int c) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{r, c});

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int cr = cur[0];
            int cc = cur[1];

            if (cr < 0 || cr >= N || cc < 0 || cc >= N) {
                continue;
            }

            if (map[cr][cc]) {
                continue;
            }

            map[cr][cc] = true;

            for (int[] delta : deltas) {
                int nr = cr + delta[0];
                int nc = cc + delta[1];
                dq.addLast(new int[]{nr, nc});
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        minH = Integer.MAX_VALUE;
        maxH = Integer.MIN_VALUE;
        answer = 1;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                minH = Math.min(minH, map[i][j]);
                maxH = Math.max(maxH, map[i][j]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(answer);
    }
}
