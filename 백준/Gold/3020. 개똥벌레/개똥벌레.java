import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int H;

    private static int[] bottom;
    private static int[] top;

    private static int min;
    private static int minSection;

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        H = Integer.parseInt(tokens.nextToken());

        bottom = new int[N / 2];
        top = new int[N / 2];

        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                bottom[i / 2] = h;
            } else {
                top[i / 2] = h;
            }
        }

        Arrays.sort(bottom);
        Arrays.sort(top);
        min = Integer.MAX_VALUE;
        minSection = 0;
    }

    private static void solve() {
        for (int h = 1; h <= H; h++) {
            int bottomCnt = calcBottom(h);
            int topCnt = calcTop(h);
            if (min == bottomCnt + topCnt) {
                minSection++;
            } else if (min > bottomCnt + topCnt) {
                min = bottomCnt + topCnt;
                minSection = 1;
            }
        }
    }

    private static int calcBottom(int h) {
        int left = 0;
        int right = (N / 2) - 1;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (bottom[mid] >= h) {
                answer = (N / 2) - mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private static int calcTop(int h) {
        h = H - h + 1;
        int left = 0;
        int right = (N / 2) - 1;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (top[mid] >= h) {
                answer = (N / 2) - mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        init();
        solve();
        sb.append(min).append(" ").append(minSection);
        System.out.println(sb);
    }
}