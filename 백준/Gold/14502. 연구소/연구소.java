import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int M;
    private static int[][] deltas = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    private static int[][] map;
    private static int answer;

    private static int countSafeArea(int[][] map) {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    private static void BFS(int[][] map) {
        boolean[][] visited = new boolean[N][M];
        ArrayDeque<int[]> dq = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    dq.push(new int[]{i, j});
                }
            }
        }

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int x = cur[0];
            int y = cur[1];

            // 범위를 벗어나면
            if (x < 0 || x >= N || y < 0 || y >= M) {
                continue;
            }
            // 벽이면
            if (map[x][y] == 1) {
                continue;
            }
            // 이미 방문했으면
            if (visited[x][y]) {
                continue;
            }

            visited[x][y] = true;
            map[x][y] = 2;

            for (int[] delta : deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];
                dq.addLast(new int[]{nx, ny});
            }
        }
    }

    private static void solution() {
        // 벽 세우기
        for (int i = 0; i < N * M; i++) {
            if (map[i / M][i % M] != 0) continue;

            for (int j = i + 1; j < N * M; j++) {
                if (map[j / M][j % M] != 0) continue;

                for (int k = j + 1; k < N * M; k++) {
                    if (map[k / M][k % M] != 0) continue;

                    // 배열 복사
                    int[][] newMap = new int[N][M];
                    for (int l = 0; l < N; l++) {
                        newMap[l] = Arrays.copyOf(map[l], map[l].length);
                    }
                    newMap[i / M][i % M] = 1;
                    newMap[j / M][j % M] = 1;
                    newMap[k / M][k % M] = 1;

                    // 바이러스 퍼트리기 (BFS)
                    BFS(newMap);

                    // 안전 영역 개수 세기
                    int count = countSafeArea(newMap);
                    answer = Math.max(answer, count);
                }
            }
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        answer = 0;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(answer);
    }
}
