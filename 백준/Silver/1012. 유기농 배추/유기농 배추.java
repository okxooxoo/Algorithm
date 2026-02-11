import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static int N;
    private static int M;
    private static int K; // 배추 개수

    private static boolean[][] ground;

    private static int solve() {
        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (ground[i][j]) {
                    BFS(i, j);
                    answer++;
                }
            }
        }

        return answer;
    }

    private static void BFS(int r, int c) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{r, c});

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int curR = cur[0];
            int curC = cur[1];

            if (curR < 0 || curC < 0 || curR >= N || curC >= M) continue;

            if (!ground[curR][curC]) continue;
            ground[curR][curC] = false;


            for (int[] delta : deltas) {
                int newR = curR + delta[0];
                int newC = curC + delta[1];

                dq.addLast(new int[]{newR, newC});
            }
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        ground = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            tokens = new StringTokenizer(br.readLine());
            int c =  Integer.parseInt(tokens.nextToken());
            int r =  Integer.parseInt(tokens.nextToken());
            ground[r][c] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init();
            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }
}