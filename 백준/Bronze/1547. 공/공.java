import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int M; // 컵의 위치를 바꾼 횟수
    private static boolean[] cups;

    public static void main(String[] args) throws IOException {
        M = Integer.parseInt(br.readLine());
        cups = new boolean[3];
        cups[0] = true;

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken()) - 1;
            int b = Integer.parseInt(tokens.nextToken()) - 1;
            boolean temp = cups[a];
            cups[a] = cups[b];
            cups[b] = temp;
        }

        for (int i = 0; i < 3; i++) {
            if (cups[i]) {
                System.out.println(i + 1);
                break;
            }
        }
    }
}