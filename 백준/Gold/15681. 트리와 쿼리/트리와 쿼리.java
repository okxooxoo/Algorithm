import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N; // 트리 정점의 수
    private static int R; // 루트 번호
    private static int Q; // 쿼리의 수

    private static ArrayList<Integer>[] connect;
    private static ArrayList<Integer>[] tree;
    private static int[] size; // n번 노드를 루트로 하는 서브 트리 사이즈

    private static void makeTree(int currentNode, int parentNode) {
        for (int node : connect[currentNode]) {
            if (node == parentNode) continue;

            tree[currentNode].add(node);
            makeTree(node, currentNode);
        }
    }

    private static void countSubtreeNodes(int currentNode) {
        size[currentNode] = 1;
        for (int node : tree[currentNode]) {
            countSubtreeNodes(node);
            size[currentNode] += size[node];
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());
        Q = Integer.parseInt(tokens.nextToken());

        connect = new ArrayList[N + 1];
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            connect[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        size = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            connect[a].add(b);
            connect[b].add(a);
        }
    }

    private static void solve() throws IOException {
        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());
            sb.append(size[q]).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        makeTree(R, -1);
        countSubtreeNodes(R);
        solve();
        System.out.println(sb);
    }
}
