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

    private static int N;
    private static int[][] map;
    private static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static int dijkstra() {
        int[][] distance = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        boolean[][] visited = new boolean[N][N];

        pq.offer(new int[] {map[0][0], 0, 0}); // 비용, row, col
        distance[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0];
            int r = cur[1];
            int c = cur[2];

            if (r == N - 1 && c == N - 1) return cost;
            if (visited[r][c]) continue;
            visited[r][c] = true;

            for (int[] delta : deltas) {
                int nr = r + delta[0];
                int nc = c + delta[1];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                int newCost = cost + map[nr][nc];
                if (distance[nr][nc] > newCost) {
                    distance[nr][nc] = newCost;
                    pq.add(new int[] {newCost, nr, nc});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        int index = 1;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            // 입력
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int cost = Integer.parseInt(tokens.nextToken());
                    map[i][j] = cost;
                }
            }

            int result = dijkstra();
            sb.append("Problem ").append(index++).append(": ").append(result).append("\n");
        }

        System.out.println(sb);
    }
}