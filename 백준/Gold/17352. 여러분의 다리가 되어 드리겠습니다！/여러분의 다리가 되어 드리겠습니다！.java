import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int[] parents; // 수열

    private static void initSet() {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = -1;
        }
    }

    private static int getParent(int node) {
        if (parents[node] < 0) {
            return node;
        }

        return getParent(parents[node]);
    }

    private static boolean isSameSet(int x, int y) {
        return getParent(x) == getParent(y);
    }

    private static void union(int x, int y) {
        if (isSameSet(x, y)) return;

        int xParent = getParent(x); // x 집합의 부모
        int yParent = getParent(y); // y 집합의 부모

        if (parents[xParent] >= parents[yParent]) {
            parents[yParent] += parents[xParent];
            parents[xParent] = yParent;
        } else {
            parents[xParent] += parents[yParent];
            parents[yParent] = xParent;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        initSet();

        for (int i = 0; i < N - 2; i++) {
            tokens = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokens.nextToken()) - 1;
            int y = Integer.parseInt(tokens.nextToken()) - 1;
            union(x, y);
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (i == j) continue;
                if (isSameSet(i, j)) continue;

                System.out.print((i + 1) + " " + (j + 1));
                return;
            }
        }
    }
}