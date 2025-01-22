import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int M;

    // 간선 리스트
    private static PriorityQueue<Edge> graph;
    private static int[] parents;
    private static int setCount; // 집합의 개수
    private static int answer;

    private static void initSet() {
        parents = new int[N];
        setCount = N;

        // 음수로 트리의 높이 표현
        for (int i = 0; i < N; i++) {
            parents[i] = -1;
        }
    }

    private static int findSet(int u) {
        if (parents[u] < 0) {
            return u;
        }

        return parents[u] = findSet(parents[u]);
    }

    private static boolean isSameSet(int u, int v) {
        return findSet(u) == findSet(v);
    }

    private static void union(int u, int v) {
        int uRoot = findSet(u);
        int vRoot = findSet(v);

        if (uRoot == vRoot) {
            parents[uRoot] = vRoot;
            parents[vRoot] -= 1;
        // v 집합이 더 depth가 깊으면
        } else if (uRoot > vRoot) {
            parents[uRoot] = vRoot;
        } else {
            parents[vRoot] = uRoot;
        }
    }

    private static void solution() {
        if (N == 2) return;
        
        while (!graph.isEmpty()) {
            Edge edge = graph.poll();
            int start = edge.startNode;
            int end = edge.endNode;

            if (!isSameSet(start, end)) {
                union(start, end);
                answer += edge.weight;
                setCount--;

                if (setCount == 2) {
                    return;
                }
            }
        }
        
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        graph = new PriorityQueue<>(new Comparator<Edge>() {

            // 간선 가중치가 적은 순부터 정렬
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(tokens.nextToken()) - 1;
            int B = Integer.parseInt(tokens.nextToken()) - 1;
            int C = Integer.parseInt(tokens.nextToken());

            // 양방향 그래프
            graph.add(new Edge(A, B, C));
        }
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