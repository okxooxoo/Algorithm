import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        tokens = new StringTokenizer(br.readLine());

        int[] line = new int[N];

        for (int i = 0; i < N; i++) {
            int leftNum = Integer.parseInt(tokens.nextToken());

            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if (line[j] == 0) {
                    if (cnt == leftNum) {
                        line[j] = i + 1;
                        break;
                    }

                    cnt++;
                }
            }
        }

        // 출력
        for (int i = 0; i < N; i++) {
            sb.append(line[i]).append(" ");
        }

        System.out.println(sb);
    }

}