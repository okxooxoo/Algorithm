import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 헛간 개수
    private static int M; // 헛간을 잇는 길 개수

    private static ArrayList<ArrayList<int[]>> graph;
    private static int[] distance;

    private static void dijkstra(int start) {
        // 최소 힙 초기화
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });

        // 각 노드의 방문 여부 초기화
        boolean[] visited = new boolean[N + 1];

        // 시작 노드 설정
        pq.add(new int[]{start, 0});
        distance[start] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];

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
                    pq.add(new int[]{nextNode, cost});
                }
            }
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        // 그래프 초기화
        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 시작 노드로부터의 최단 거리 초기화
        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(tokens.nextToken());
            int B = Integer.parseInt(tokens.nextToken());
            int cost = Integer.parseInt(tokens.nextToken());
            // 양방향 그래프
            graph.get(A).add(new int[]{B, cost});
            graph.get(B).add(new int[]{A, cost});
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        dijkstra(1);
        System.out.println(distance[N]);
    }
}