import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int M;

    private static int[] A; // 수열

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(A);

        int answer = Integer.MAX_VALUE;
        int left = 0, right = 0;

        while (right < N) {
            int d = A[right] - A[left];
            if (d >= M) {
                answer = Math.min(answer, d);
                left++;
                if (left > right) right++;
            } else {
                right++;
            }
        }

        System.out.println(answer);
    }
}