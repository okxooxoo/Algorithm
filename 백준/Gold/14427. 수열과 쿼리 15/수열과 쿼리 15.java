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
    
    private static int[] numbers; // 수열
    private static int[] tree; // 구간별 최솟값을 저장하는 세그먼트 트리

    // 수열의 최솟값의 인덱스 반환
    private static int getMinIndex() {
        int min = tree[1];
        for (int i = 0; i < N; i++) {
            if (numbers[i] == min) {
                return i + 1;
            }
        }
        return -1;
    }

    // 세그먼트 트리 초기화
    private static int init(int start, int end, int node) {
        if (start == end) {
            tree[node] = numbers[start];
            return tree[node];
        }
        int mid = (start + end) / 2;
        tree[node] = Math.min(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
        return tree[node];
    }

    // 세그먼트 트리 업데이트
    private static void update(int start, int end, int node, int index) {
        if (start > index || end < index) {
            return;
        }
        if (start == end) {
            tree[node] = numbers[index];
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index);
        update(mid + 1, end, node * 2 + 1, index);
        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        tokens = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(tokens.nextToken());
        }

        // 트리의 높이
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        // 트리 내 노드 개수
        int nodeNum = (int) Math.pow(2, h+1);
        tree = new int[nodeNum];

        init(0, N-1, 1);

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokens.nextToken());
            if (cmd == 1) {
                // 1번 쿼리 실행
                int index = Integer.parseInt(tokens.nextToken()) - 1;
                int value = Integer.parseInt(tokens.nextToken());
                numbers[index] = value;
                update(0, N-1, 1, index);
            } else {
                // 2번 쿼리 실행
                sb.append(getMinIndex()).append("\n");
            }
        }
        System.out.println(sb);
    }
}