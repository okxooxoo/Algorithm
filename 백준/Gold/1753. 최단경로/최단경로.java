import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int V;
    private static int E;
    private static int start;

    private static ArrayList<ArrayList<int[]>> graph;
    private static boolean[] visited;
    private static int[] distance;
    private static PriorityQueue<int[]> pq;

    private static void dijkstra() {
        pq.add(new int[]{0, start});
        distance[start] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curCost = cur[0];
            int curNode = cur[1];
            // 방문 여부 확인
            if (visited[curNode]) {
                continue;
            }
            // 방문 처리
            visited[curNode] = true;
            // 인접 노드 탐색
            for (int[] next : graph.get(curNode)) {
                int nextNode = next[0];
                int nextCost = next[1];

                int cost = curCost + nextCost;
                if (distance[nextNode] > cost) {
                    distance[nextNode] = cost;
                    pq.add(new int[]{cost, nextNode});
                }
            }
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        V = Integer.parseInt(tokens.nextToken());
        E = Integer.parseInt(tokens.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        visited = new boolean[V + 1];
        distance = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        start = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            tokens = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(tokens.nextToken());
            int v = Integer.parseInt(tokens.nextToken());
            int w = Integer.parseInt(tokens.nextToken());

            graph.get(u).add(new int[] {v, w});
        }
    }

    private static void output() {
        for (int i = 1; i <= V; i++) {
            int temp = distance[i];
            if (temp == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
            } else {
                sb.append(temp).append("\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();
        output();
        System.out.println(sb);
    }
}