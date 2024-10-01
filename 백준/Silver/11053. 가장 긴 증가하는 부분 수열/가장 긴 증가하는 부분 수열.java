import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[] arr;
    private static int[] lis;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        lis = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
        }
    }

    private static int binarySearch(int left, int right, int value) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (lis[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    private static int solve() {
        int len = 0; // lis 배열의 길이
        int idx = 0;
        lis[len] = arr[0];

        for (int i = 1; i < N; i++) {
            if (lis[len] < arr[i]) {
                len++;
                lis[len] = arr[i];
            } else {
                idx = binarySearch(0, len, arr[i]);
                lis[idx] = arr[i];
            }
        }

        return len + 1;
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }
}