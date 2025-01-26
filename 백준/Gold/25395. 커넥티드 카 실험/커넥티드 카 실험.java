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
    private static int S;

    // 모든 자동차들의 위치
    private static int[] carPosition;
    // 연결 가능성이 있는 자동차들끼리 연결
    private static ArrayList<ArrayList<Integer>> graph;

    private static void bfs() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        dq.offer(S - 1);

        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();

            if (visited[cur]) {
                continue;
            }

            visited[cur] = true;

            for (int next : graph.get(cur)) {
                dq.addLast(next);
            }
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                sb.append(i + 1).append(" ");
            }
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        S = Integer.parseInt(tokens.nextToken());

        carPosition = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            carPosition[i] = Integer.parseInt(tokens.nextToken());
        }

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        tokens = new StringTokenizer(br.readLine());
        int pointer;

        for (int i = 0; i < N; i++) {
            int fuel = Integer.parseInt(tokens.nextToken());

            // 왼쪽 방향으로 연결 가능성 있는 자동차를 그래프에 삽입
            pointer = i - 1;
            while (pointer >= 0) {
                if (carPosition[i] - carPosition[pointer] <= fuel) {
                    graph.get(i).add(pointer);
                    pointer--;
                } else {
                    break;
                }
            }

            // 오른쪽 방향으로 연결 가능성 있는 자동차를 그래프에 삽입
            pointer = i + 1;
            while (pointer < N) {
                if (carPosition[pointer] - carPosition[i] <= fuel) {
                    graph.get(i).add(pointer);
                    pointer++;
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        System.out.println(sb);
    }
}