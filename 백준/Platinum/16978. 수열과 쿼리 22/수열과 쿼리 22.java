import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int M;

    private static int[] numbers; // 수열
    // 구간합을 구하기 위한 세그먼트 트리
    // 루트 노트 최대 크기 1조(100만 * 100만)이므로 long 배열로 선언
    private static long[] tree;

    private static ArrayList<int[]> query1;
    private static PriorityQueue<int[]> query2;

    private static PriorityQueue<long[]> output; // 출력할 구간합

    public static void main(String[] args) throws IOException {
        input();
        solution();
        // 출력
        while (!output.isEmpty()) {
            sb.append(output.poll()[1]).append("\n");
        }
        System.out.println(sb);
    }

    private static void solution() {
        for (int k = 0; k < query1.size(); k++) {
            // k번째 1번 쿼리까지 적용되었을 때의 구간합 구하기
            while (!query2.isEmpty() && query2.peek()[0] == k) {
                int[] q = query2.poll();
                int left = q[1] - 1;
                int right = q[2] - 1;
                long sectionSum = getSectionSum(0, N-1, 1, left, right);
                output.add(new long[]{q[3], sectionSum});
            }

            // 1번 쿼리 실행하여 수열 변경
            int index = query1.get(k)[0] - 1;
            int value = query1.get(k)[1];
            numbers[index] = value;
            update(0, N-1, 1, index);
        }

        // 모든 1번 쿼리가 처리된 후 남은 2번 쿼리들 처리
        while (!query2.isEmpty()) {
            int[] q = query2.poll();
            int left = q[1] - 1;
            int right = q[2] - 1;
            long sectionSum = getSectionSum(0, N-1, 1, left, right);
            output.add(new long[]{q[3], sectionSum});
        }
    }

    // 입력
    private static void input() throws IOException {
        // 수열 입력 받기
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            numbers[n] = Integer.parseInt(tokens.nextToken());
        }

        // 쿼리 입력 받기
        M = Integer.parseInt(br.readLine());
        query1 = new ArrayList<>();
        query2 = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int index = 0; // 답 출력 순서

        for (int m = 0; m < M; m++) {
            tokens = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokens.nextToken());
            if (cmd == 1) {
                int i = Integer.parseInt(tokens.nextToken());
                int v = Integer.parseInt(tokens.nextToken());
                query1.add(new int[]{i, v});
            } else {
                int k = Integer.parseInt(tokens.nextToken());
                int i = Integer.parseInt(tokens.nextToken());
                int j = Integer.parseInt(tokens.nextToken());
                query2.add(new int[]{k, i, j, index++});
            }
        }

        output = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        // 세그먼트 트리 초기화
        tree = new long[N * 4];
        init(0, N - 1, 1);
    };

    // 세그먼트 트리 초기화
    private static long init(int start, int end, int node) {
        if (start == end) {
            tree[node] = numbers[start];
            return tree[node];
        }

        int mid = (start + end) / 2;
        tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
        return tree[node];
    }

    // 수열의 변경이 일어나 세그먼트 트리 업데이트
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
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    // 구간합 구하기
    private static long getSectionSum(int start, int end, int node, int left, int right) {
        // 범위 밖에 있는 경우
        if (start > right || end < left) {
            return 0;
        }
        // 범위 안에 있는 경우
        if (start >= left && end <= right) {
            return tree[node];
        }
        // 둘 다 아닌 경우
        int mid = (start + end) / 2;
        return getSectionSum(start, mid, node * 2, left, right)
                + getSectionSum(mid + 1, end, node * 2 + 1, left, right);
    }
}