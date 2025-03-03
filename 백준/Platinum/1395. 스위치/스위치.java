import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N; // 스위치 개수
    private static int M; // 처리할 일의 개수

    private static int[] tree; // 세그먼트 트리 (0이면 꺼져 있는 상태, 1이면 켜져 있는 상태)
    private static int[] lazy;

    // 현재 노드의 정보를 자식 노드에게 전달
    private static void propagate(int start, int end, int index) {
        // 홀수 번 눌렀으면 스위치가 반전되어야 하므로 갱신
        if (lazy[index] % 2 == 1) {
            // 자식 노드에게 전달
            if (start != end) {
                lazy[index * 2] += lazy[index];
                lazy[index * 2 + 1] += lazy[index];
            }

            tree[index] = (end - start + 1) - tree[index];
            lazy[index] = 0;
        }
    }

    // 켜져 있는 스위치 개수 반환
    private static int getSwitchOnCount(int start, int end, int index, int left, int right) {
        // 필요한 구간만 갱신(느리게 갱신)
        propagate(start, end, index);

        if (start > right || end < left) {
            return 0;
        }

        if (left <= start && right >= end) {
            return tree[index];
        }

        int mid = (start + end) / 2;
        return getSwitchOnCount(start, mid, index * 2, left, right) + getSwitchOnCount(mid + 1, end, index * 2 + 1, left, right);
    }

    // 해당 구간의 스위치 반전
    private static void reverseSwitch(int start, int end, int index, int left, int right) {
        // 필요한 구간만 갱신(느리게 갱신)
        propagate(start, end, index);

        if (start > right || end < left) {
            return;
        }

        if (left <= start && right >= end) {
            lazy[index] = 1;
            propagate(start, end, index);
            return;
        }

        int mid = (start + end) / 2;
        reverseSwitch(start, mid, index * 2, left, right);
        reverseSwitch(mid + 1, end, index * 2 + 1, left, right);

        tree[index] = tree[index * 2] + tree[index * 2 + 1];
    }

    private static void solution() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        tree = new int[N * 4];
        lazy = new int[N * 4];

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int O = Integer.parseInt(tokens.nextToken());

            // S번부터 T번 스위치까지 상태 반전
            if (O == 0) {
                int S = Integer.parseInt(tokens.nextToken()) - 1;
                int T = Integer.parseInt(tokens.nextToken()) - 1;
                reverseSwitch(0, N - 1, 1, S, T);
            // S번부터 T번 스위치까지 중 켜져 있는 스위치 개수 출력
            } else {
                int S = Integer.parseInt(tokens.nextToken()) - 1;
                int T = Integer.parseInt(tokens.nextToken()) - 1;
                int count = getSwitchOnCount(0, N - 1, 1, S, T);
                sb.append(count).append("\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }
}