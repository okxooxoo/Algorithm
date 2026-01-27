import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[][] graph;

    private static int solve() {
        boolean[] visited = new boolean[101];
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        dq.add(1);

        while (!dq.isEmpty()) {
            int com = dq.pollFirst();

            if (visited[com]) continue;
            visited[com] = true; // 방문 처리

            for (int i = 1; i <= N; i++) {
                if (graph[com][i] == 1) {
                    dq.addLast(i);
                }
            }
        }

        int answer = 0;

        for (int i = 2; i <= N; i++) {
            if (visited[i]) {
                answer++;
            }
        }

        return answer;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new int[101][101];

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            graph[a][b] = 1;
            graph[b][a] = 1;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }
}