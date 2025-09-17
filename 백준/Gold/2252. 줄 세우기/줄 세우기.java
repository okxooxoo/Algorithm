import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer tokens;

    private static int N; // 사람 수
    private static int M; // 키에 대한 정보 수

    private static ArrayList<ArrayList<Integer>> graph;
    private static int[] inOrder; // 진입 차수

    private static ArrayDeque<Integer> dq;

    private static void solution() throws IOException {
        for (int i = 1; i <= N; i++) {
            if (inOrder[i] == 0) {
                dq.addLast(i);
            }
        }

        while (!dq.isEmpty()) {
            int person = dq.poll();

            for (int next : graph.get(person)) {
                inOrder[next]--;

                if (inOrder[next] == 0) {
                    dq.addLast(next);
                }
            }

            bw.write(person + " ");
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        inOrder = new int[N + 1];
        dq = new ArrayDeque<>();
        
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            graph.get(a).add(b);
            inOrder[b]++;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        bw.flush();
        bw.close();
    }
}
