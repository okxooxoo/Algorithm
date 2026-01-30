import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 정점의 개수
    private static int M; // 간선의 개수

    private static boolean[][] graph;

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        graph = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            graph[a][b] = true;
            graph[b][a] = true;
        }
    }

    private static void BFS(int start, boolean[] visited) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(start);

        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();

            if (visited[cur]) continue;
            visited[cur] = true;

            for (int i = 1; i <= N; i++) {
                if (graph[cur][i]) {
                    dq.addLast(i);
                }
            }
        }
    }

    private static int solve() {
        int answer = 0;
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                BFS(i, visited);
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }
}