import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int V;
    private static int E;

    private static Edge[] edges;
    private static int[] rank;
    private static int[] parents;

    private static void make() {
        rank = new int[V + 1];
        parents = new int[V + 1];

        Arrays.fill(rank, 1);

        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int a) {
        if (parents[a] == a) return a;
        // 집합의 대표자를 자신의 부모로 변경!
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) return false;

        // 집합 크기 관리
        if (rank[aRoot] > rank[bRoot]) {
            // aRoot로 흡수
            parents[bRoot] = aRoot;
        } else if (rank[aRoot] < rank[bRoot]) {
            // bRoot로 흡수
            parents[aRoot] = bRoot;
        } else {
            parents[bRoot] = aRoot;
            rank[aRoot]++;
        }
        return true;
    }

    private static void init() throws IOException {
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        edges = new Edge[E];

        for (int i = 0; i < E; i++) {
            tokens = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());
            int weight = Integer.parseInt(tokens.nextToken());
            edges[i] = new Edge(start, end, weight);
        }

        Arrays.sort(edges); // 간선의 가중치를 기준으로 오름차순 정렬
    }

    private static int solution() {
        int count = 0;
        int cost = 0;

        for (Edge edge : edges) {
            if (union(edge.start, edge.end)) {
                cost += edge.weight;
                // 최소 신장 트리 완성
                if (++count == V - 1) break;
            }
        }
        return cost;
    }

    public static void main(String[] args) throws IOException {
        init();
        make();
        System.out.println(solution());
    }

    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            super();
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}