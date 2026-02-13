import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int K;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        ArrayDeque<Integer> dq = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            dq.addLast(i);
        }

        int num;
        sb.append("<");

        while (true) {
            for (int i = 1; i < K; i++) {
                if (dq.isEmpty()) break;
                num = dq.pollFirst();
                dq.addLast(num);
            }

            if (dq.isEmpty()) break;
            num = dq.pollFirst();
            sb.append(num);

            if (dq.isEmpty()) break;
            sb.append(", ");
        }

        sb.append(">");

        System.out.println(sb);
    }
}