import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 식탁의 길이
    private static int K; // 햄버거를 먹을 수 있는 거리

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        char[] table = br.readLine().toCharArray();

        boolean[] ate = new boolean[N];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (table[i] == 'H') continue;

            for (int j = i - K; j <= i + K; j++) {
                if (j < 0 || j >= N) continue;
                if (table[j] == 'P' || ate[j]) continue;

                ate[j] = true;
                answer++;
                break;
            }
        }

        System.out.println(answer);
    }
}