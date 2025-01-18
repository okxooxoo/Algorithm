import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int M;

    private static int[] units; // 부대별 수용 인원수
    private static long[] tree;

    // 세그먼트 트리 초기화
    private static long init(int start, int end, int index) {
        // 리프 노드에 도달하면
        if (start == end) {
            tree[index] = units[start];
            return tree[index];
        }

        int mid = (start + end) / 2;
        tree[index] = init(start, mid, index * 2) + init(mid + 1, end, index * 2 + 1);
        return tree[index];
    }

    // 세그먼트 트리 업데이트
    private static void update(int start, int end, int index, int target, int value) {
        // 범위 밖에 있는 경우
        if (start > target || end < target) {
            return;
        }

        // 범위 안에 있는 경우
        tree[index] += value;

        // 리프 노드면 재귀 종료
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, index * 2, target, value);
        update(mid + 1, end, index * 2 + 1, target, value);
    }

    // 해당 군인이 몇 번 부대에 배치받았는지 반환
    private static int findUnit(int start, int end, int index, long target) {
        if (start == end) {
            return start + 1;
        }

        int mid = (start + end) / 2;

        if (tree[index * 2] >= target) {
            return findUnit(start, mid, index * 2, target);
        } else {
            return findUnit(mid + 1, end, index * 2 + 1, target - tree[index * 2]);
        }

    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        units = new int[N];
        tree = new long[N * 4];
        tokens = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            units[i] = Integer.parseInt(tokens.nextToken());
        }

        init(0, N - 1, 1);

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokens.nextToken());
            if (cmd == 1) {
                // 부대 인원수 변경
                int a = Integer.parseInt(tokens.nextToken()) - 1;
                int b = Integer.parseInt(tokens.nextToken());
                units[a] += b;
                update(0, N - 1, 1, a, b);
            } else {
                int a = Integer.parseInt(tokens.nextToken());
                int answer = findUnit(0, N - 1, 1, a);
                sb.append(answer).append("\n");
            }
        }

        System.out.println(sb);
    }
}