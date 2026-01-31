import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[] numbers;
    private static int[] operator;
    private static int mx;
    private static int mn;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        operator = new int[4];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(tokens.nextToken());
        }

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(tokens.nextToken());
        }

        mx = Integer.MIN_VALUE;
        mn = Integer.MAX_VALUE;
    }

    private static void DFS(int depth, int num) {
        if (depth == N) {
            mx = Math.max(mx, num);
            mn = Math.min(mn, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                operator[i]--;

                if (i == 0) {
                    DFS(depth + 1, num + numbers[depth]);
                } else if (i == 1) {
                    DFS(depth + 1, num - numbers[depth]);
                } else if (i == 2) {
                    DFS(depth + 1, num * numbers[depth]);
                } else {
                    DFS(depth + 1, num / numbers[depth]);
                }

                operator[i]++;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        init();
        DFS(1, numbers[0]);
        System.out.println(mx);
        System.out.println(mn);
    }
}