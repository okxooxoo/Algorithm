import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int V; // 정점의 개수
    private static int E; // 간선의 개수
    private static PriorityQueue<Edge> graph; // 간선 리스트
    
    private static int[] parents; // 집합 관리

    private static int answer;

    private static void solution() {
        while (!graph.isEmpty()) {
            Edge edge = graph.poll();
            int startNode = edge.startNode;
            int endNode = edge.endNode;

            if (unionSet(startNode, endNode)) {
                answer += edge.weight;
            }
        }
    }

    private static void initSet() {
        parents = new int[V];

        // 음수로 트리의 높이 표현
        for (int i = 0; i < V; i++) {
            parents[i] = -1;
        }
    }

    private static int findSet(int a) {
        if (parents[a] < 0) {
            return a;
        }

        return parents[a] = findSet(parents[a]);
    }

    private static boolean unionSet(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) return false; // 같은 집합

        if (parents[aRoot] == parents[bRoot]) {
            parents[bRoot] = aRoot;
            parents[aRoot]--;
        } else if (parents[aRoot] > parents[bRoot]) {
            parents[aRoot] = bRoot;
        } else {
            parents[bRoot] = aRoot;
        }

        return true;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        V = Integer.parseInt(tokens.nextToken());
        E = Integer.parseInt(tokens.nextToken());

        graph = new PriorityQueue<>(new Comparator<Edge>() {
            // 간선 가중치가 작은 순부터 정렬
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        for (int i = 0; i < E; i++) {
            tokens = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(tokens.nextToken()) - 1;
            int B = Integer.parseInt(tokens.nextToken()) - 1;
            int C = Integer.parseInt(tokens.nextToken());

            graph.add(new Edge(A, B, C));
        }

        answer = 0;
    }

    public static void main(String[] args) throws IOException {
        init();
        initSet();
        solution();
        System.out.println(answer);
    }

    static class Edge {
        int startNode;
        int endNode;
        int weight;

        Edge(int startNode, int endNode, int weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }
    }
}
