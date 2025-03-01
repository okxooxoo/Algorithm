import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int M;

    private static int[] inDegree;
    private static ArrayList<ArrayList<Integer>> graph;

    private static int[] answer;

    private static void solution() {
        ArrayDeque<int[]> dq = new ArrayDeque<>();

        // 선수 과목이 없는 과목을 덱에 삽입
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                dq.add(new int[] {i, 1});
            }
        }

        while (!dq.isEmpty()) {
            int[] node = dq.poll();
            int subject = node[0];
            int term = node[1];

            // 최소 몇 학기에 이수할 수 있는지 기록
            answer[subject] = term;

            for (int next : graph.get(subject)) {
                inDegree[next]--;

                if (inDegree[next] == 0) {
                    dq.add(new int[] {next, term + 1});
                }
            }
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        inDegree = new int[N + 1];
        graph = new ArrayList<>();
        answer = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());

            graph.get(a).add(b);
            inDegree[b]++;
        }
    }

    private static void output() {
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        output();
    }
}