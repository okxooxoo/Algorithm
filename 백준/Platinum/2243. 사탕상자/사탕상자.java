import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int n;

    private static int[] tree;

    // 사탕 맛 최댓값
    private static final int TASTE_MAX_VALUE = 1_000_000;

    // taste 맛 사탕을 num 만큼 꺼내거나 넣는다
    private static void update(int start, int end, int index, int taste, int num) {
        if (start > taste || end < taste) {
            return;
        }

        tree[index] += num;

        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, index * 2, taste, num);
        update(mid + 1, end, index * 2 + 1, taste, num);
    }

    // rank 순위의 사탕의 맛을 반환한다
    private static int getCandy(int start, int end, int index, int rank) {
        if (start == end) {
            return start;
        }

        int mid = (start + end) / 2;

        if (tree[index * 2] >= rank) {
            return getCandy(start, mid, index * 2, rank);
        } else {
            return getCandy(mid + 1, end, index * 2 + 1, rank - tree[index * 2]);
        }
    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        tree = new int[TASTE_MAX_VALUE * 4];

        for (int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(tokens.nextToken());
            if (A == 1) {
                int rank = Integer.parseInt(tokens.nextToken());
                // rank 순위의 사탕을 꺼낸다
                int taste = getCandy(0, TASTE_MAX_VALUE - 1, 1, rank);
                update(0, TASTE_MAX_VALUE - 1, 1, taste, -1);
                sb.append(taste + 1).append("\n");
            } else {
                int taste = Integer.parseInt(tokens.nextToken()) - 1;
                int num = Integer.parseInt(tokens.nextToken());
                // taste 맛 사탕을 num 만큼 꺼낸다
                update(0, TASTE_MAX_VALUE - 1, 1, taste, num);
            }
        }

        System.out.println(sb);
    }
}