import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int M;

    private static int[][] graph;
    // 각 원소가 속한 집합의 대표자
    private static int[] parents;

    // 모든 집합 초기화
    private static void initSet() {
        parents = new int[N];

        // 각 트리의 높이를 음수로 표현
        for (int i = 0; i < N; i++) {
            parents[i] = -1;
        }
    }

    // 해당 원소가 속한 집합의 대표자 반환
    private static int findSet(int a) {
        // 음수면 a가 집합의 대표자
        if (parents[a] < 0) {
            return a;
        }

        parents[a] = findSet(parents[a]);
        return parents[a];
    }

    // 같은 집합에 속해있는지 여부 반환
    private static boolean isSameSet(int a, int b) {
        return findSet(a) == findSet(b);
    }

    // 합집합 연산
    private static boolean unionSet(int a, int b) {
        if (isSameSet(a, b)) {
            return false;
        }

        int aRoot = findSet(a);
        int bRoot = findSet(b);

        // 두 트리의 높이가 같다면
        if (aRoot == bRoot) {
            parents[aRoot] = bRoot;
            parents[bRoot] -= 1; // 높이 1 증가
        // b 트리의 높이가 더 크다면 a 트리를 b 트리에 흡수
        } else if (aRoot > bRoot) {
            parents[aRoot] = bRoot;
        // a 트리의 높이가 더 크다면 b 트리를 a 트리에 흡수
        } else {
            parents[bRoot] = aRoot;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        initSet();

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(tokens.nextToken());
                if (graph[i][j] == 1) {
                    // 두 도시가 연결되어 있으면 합집합 연산
                    unionSet(i, j);
                }
            }
        }

        boolean flag = true; // 방문할 모든 도시가 연결되어 있는지 여부

        tokens = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(tokens.nextToken()) - 1;

        for (int i = 1; i < M; i++) {
            int endCity = Integer.parseInt(tokens.nextToken()) - 1;
            if (!isSameSet(startCity, endCity)) {
                flag = false;
            }
            startCity = endCity;
        }

        if (flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}