import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int M;
    private static int[][] tree;

    private static int BFS(int a, int b) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        q.offer(new int[] {a, 0});

        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();
            int curNode = cur[0];
            int curD = cur[1];

            if (curNode == b) {
                return curD;
            }

            if (visited[curNode]) continue;
            visited[curNode] = true;

            for (int i = 1; i <= N; i++) {
                if (tree[curNode][i] > 0) {
                    q.addLast(new int[] {i, curD + tree[curNode][i]});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        tree = new int[N + 1][N + 1];
        for (int i = 0; i < N - 1; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int d = Integer.parseInt(tokens.nextToken());
            tree[a][b] = d;
            tree[b][a] = d;
        }

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            sb.append(BFS(a, b)).append("\n");
        }

        System.out.println(sb);
    }
}