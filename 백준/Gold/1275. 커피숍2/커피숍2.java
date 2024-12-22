import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N; // 수의 개수
    private static int Q; // 턴의 개수

    private static long[] numbers;
    private static long[] tree;

    private static long init(int start, int end, int node) {
        if (start == end) {
            tree[node] = numbers[start];
            return tree[node];
        }

        int mid = (start + end) / 2;
        tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
        return tree[node];
    }

    private static void update(int start, int end, int node, int index) {
        if (start > index || end < index ) {
            return;
        }
        if (start == end) {
            tree[node] = numbers[index];
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index);
        update(mid + 1, end, node * 2 + 1, index);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static long getSectionSum(int start, int end, int node, int left, int right) {
        if (start > right || end < left) {
            return 0;
        }
        if (start >= left && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return getSectionSum(start, mid, node * 2, left, right)
                + getSectionSum(mid + 1, end, node * 2 + 1, left, right);
    }

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        Q = Integer.parseInt(tokens.nextToken());

        numbers = new long[N];

        tokens = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(tokens.nextToken());
        }

        tree = new long[N * 4];
        init(0, N-1, 1);

        for (int i = 0; i < Q; i++) {
            tokens = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokens.nextToken()) - 1;
            int y = Integer.parseInt(tokens.nextToken()) - 1;
            int a = Integer.parseInt(tokens.nextToken()) - 1;
            long b = Long.parseLong(tokens.nextToken());

            if (x <= y) {
                sb.append(getSectionSum(0, N-1, 1, x, y)).append("\n");
            } else {
                sb.append(getSectionSum(0, N-1, 1, y, x)).append("\n");
            }

            numbers[a] = b;
            update(0, N-1, 1, a);
        }

        System.out.println(sb);
    }
}