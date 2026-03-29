import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n; // 정점 개수
    private static int m; // 간선 개수

    private static ArrayList<ArrayList<int[]>> graph;
    private static int[] distances;
    private static int start;
    private static int end;

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            tokens = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());
            int w = Integer.parseInt(tokens.nextToken());
            graph.get(x).add(new int[]{y, w});
            graph.get(y).add(new int[]{x, w});
        }

        tokens = new StringTokenizer(br.readLine());
        start = Integer.parseInt(tokens.nextToken());
        end = Integer.parseInt(tokens.nextToken());
    }

    private static void solution() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        boolean[] visited = new boolean[n + 1];

        distances[start] = 0;
        pq.offer(new int[]{0, start});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curCost = cur[0];
            int curNode = cur[1];

            if (visited[curNode]) continue;
            visited[curNode] = true;

            for (int[] next : graph.get(curNode)) {
                int nextNode = next[0];
                int nextCost = next[1];

                int cost = curCost + nextCost;
                if (distances[nextNode] > cost) {
                    distances[nextNode] = cost;
                    pq.offer(new int[]{cost, nextNode});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(distances[end]);
    }
}