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

    private static int N; // 가수의 수
    private static int M; // 보조 PD의 수

    private static ArrayList<ArrayList<Integer>> graph;
    private static int[] inDegree; // 각 가수의 진입 차수
    private static ArrayDeque<Integer> dq;

    private static void solution() {
        int count = 0; // 출연 완료한 가수 수

        // 진입 차수가 0인 가수 덱에 삽입
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                dq.addLast(i);
                count++;
            }
        }

        while (!dq.isEmpty()) {
            // 가수 출연
            int singer = dq.pollFirst();
            sb.append(singer).append("\n");

            // singer와 연결된 노드의 진입 차수 감소
            for (int node : graph.get(singer)) {
                inDegree[node]--;

                if (inDegree[node] == 0) {
                    dq.addLast(node);
                    count++;
                }
            }
        }

        // 출력
        if (count == N) {
            System.out.println(sb);
        } else {
            System.out.println(0);
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        graph = new ArrayList<>();
        inDegree = new int[N + 1];
        dq = new ArrayDeque<>();

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            // 하나의 보조 PD가 담당한 가수 수
            int n = Integer.parseInt(tokens.nextToken());
            // 가장 처음 순서인 가수
            int from = Integer.parseInt(tokens.nextToken());

            for (int j = 0; j < n - 1; j++) {
                int to = Integer.parseInt(tokens.nextToken());
                graph.get(from).add(to);
                inDegree[to]++;
                from = to;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }
}