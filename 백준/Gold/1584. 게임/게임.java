import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N; // 위험한 구역의 수
    private static int M; // 죽음의 구역의 수
    private static int[][] map;

    private static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static void init() throws IOException {
        map = new int[501][501];
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(tokens.nextToken());
            int y1 = Integer.parseInt(tokens.nextToken());
            int x2 = Integer.parseInt(tokens.nextToken());
            int y2 = Integer.parseInt(tokens.nextToken());

            for (int r = Math.min(y1, y2); r <= Math.max(y1, y2); r++) {
                for (int c = Math.min(x1, x2); c <= Math.max(x1, x2); c++) {
                    map[r][c] = 1;
                }
            }
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(tokens.nextToken());
            int y1 = Integer.parseInt(tokens.nextToken());
            int x2 = Integer.parseInt(tokens.nextToken());
            int y2 = Integer.parseInt(tokens.nextToken());


            for (int r = Math.min(y1, y2); r <= Math.max(y1, y2); r++) {
                for (int c = Math.min(x1, x2); c <= Math.max(x1, x2); c++) {
                    map[r][c] = 2;
                }
            }
        }
    }

    private static int dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        boolean[][] visited = new boolean[501][501];
        int[][] distance = new int[501][501];
        for (int i = 0; i <= 500; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        pq.offer(new int[] {0, 0, 0});
        distance[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curCost = cur[0];
            int curR = cur[1];
            int curC = cur[2];

            if (curR == 500 && curC == 500) return curCost;
            if (visited[curR][curC]) continue;
            visited[curR][curC] = true;

            for (int[] delta : deltas) {
                int newR = curR + delta[0];
                int newC = curC + delta[1];

                if (newR < 0 || newC < 0 || newR > 500 || newC > 500) continue;
                if (map[newR][newC] == 2) continue;

                int cost = curCost + map[newR][newC];
                if (distance[newR][newC] > cost) {
                    distance[newR][newC] = cost;
                    pq.add(new int[] {cost, newR, newC});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(dijkstra());
    }
}