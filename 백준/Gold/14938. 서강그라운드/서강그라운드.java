import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n; // 노드 개수
    private static int m; // 수색 범위
    private static int r; // 간선 개수
    private static Node[] nodes;
    private static int[][] graph;

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());
        r = Integer.parseInt(tokens.nextToken());
        nodes = new Node[n + 1];
        graph = new int[n + 1][n + 1];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int item = Integer.parseInt(tokens.nextToken());
            Node node = new Node(i, item);
            nodes[i] = node;
        }

        for (int i = 0; i < r; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int l = Integer.parseInt(tokens.nextToken());
            graph[a][b] = l;
            graph[b][a] = l;
        }
    }

    private static void dijkstra(int start, int[] distances) {
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        pq.offer(new int[] {0, start});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curCost = cur[0];
            int curNum = cur[1];

            if (visited[curNum]) continue;
            visited[curNum] = true;

            for (int i = 1; i <= n; i++) {
                if (graph[curNum][i] == 0) continue;
                int cost = curCost + graph[curNum][i];
                if (distances[i] > cost) {
                    distances[i] = cost;
                    pq.add(new int[] {cost, i});
                }
            }
        }
    }

    private static int solve() {
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int[] distances = new int[n + 1];
            dijkstra(i, distances);

            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (distances[j] <= m) {
                    cnt += nodes[j].item;
                }
            }
            answer = Math.max(answer, cnt);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    static class Node {
        int num;
        int item;

        Node(int num, int item) {
            this.num = num;
            this.item = item;
        }
    }
}