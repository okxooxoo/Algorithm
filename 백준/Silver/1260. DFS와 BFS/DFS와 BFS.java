import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb;

    private static int N;
    private static int M;
    private static int startNode;

    private static boolean[][] graph;
    private static boolean[] DFSVisited;

    private static void DFS(int cur) {
        DFSVisited[cur] = true;
        sb.append(cur).append(" ");

        for (int i = 1; i <= N; i++) {
            if (graph[cur][i] && !DFSVisited[i]) {
                DFS(i);
            }
        }
    }

    private static void BFS() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        dq.add(startNode);
        sb.append("\n");

        while (!dq.isEmpty()) {
            int curNode = dq.pollFirst();

            if (visited[curNode]) {
                continue;
            }

            visited[curNode] = true;
            sb.append(curNode).append(" ");

            for (int i = 1; i <= N; i++) {
                if (graph[curNode][i]) {
                    dq.addLast(i);
                }
            }
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        startNode = Integer.parseInt(tokens.nextToken());

        graph = new boolean[N + 1][N + 1];
        DFSVisited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            graph[a][b] = true;
            graph[b][a] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        sb = new StringBuilder();
        DFS(startNode);
        BFS();
        System.out.println(sb);
    }
}