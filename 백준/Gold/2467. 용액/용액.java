import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[] arr;

    private static void solution() {
        int left = 0;
        int right = N - 1;

        int answer = 2_000_000_000; // 20억
        int answerLeft = 0;
        int answerRight = 0;

        while (left < right) {
            int temp = arr[left] + arr[right];

            if (Math.abs(temp) <= Math.abs(answer)) {
                answer = temp;
                answerLeft = arr[left];
                answerRight = arr[right];
            }

            if (temp > 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(answerLeft + " " + answerRight);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        tokens = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }
}
