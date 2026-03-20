import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[][] tastes;
    private static boolean[] visited;

    private static int answer;

    private static int calcDiffer() {
        int a = 1;
        int b = 0;

        for (int i = 0; i < N; i++) {
            if (!visited[i]) continue;
            a *= tastes[i][0];
            b += tastes[i][1];
        }

        return Math.abs(a - b);
    }

    private static void solution(int depth) {
        if (depth == N) {
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    flag = true;
                    break;
                }
            }

            if (!flag) return;

            int result = calcDiffer();
            answer = Math.min(answer, result);
            return;
        }

        visited[depth] = true;
        solution(depth + 1);
        visited[depth] = false;
        solution(depth + 1);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        tastes = new int[N][2];
        visited = new boolean[N];
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            tastes[i][0] = Integer.parseInt(tokens.nextToken());
            tastes[i][1] = Integer.parseInt(tokens.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution(0);
        System.out.println(answer);
    }
}