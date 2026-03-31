import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int K; // 트리 깊이
    private static int[] node;
    private static ArrayList<ArrayList<Integer>> list;

    private static void search(int start, int end, int depth) {
        if (depth == K) {
            return;
        }

        int mid = (start + end) / 2;
        list.get(depth).add(node[mid]);

        search(start, mid - 1, depth + 1);
        search(mid + 1, end, depth + 1);
    }

    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(br.readLine());
        int nodeCnt = (int)Math.pow(2, K) - 1;
        node = new int[nodeCnt];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < nodeCnt; i++) {
            node[i] = Integer.parseInt(tokens.nextToken());
        }

        list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            list.add(new ArrayList<>());
        }

        search(0, nodeCnt - 1, 0);

        for (int i = 0; i < K; i++) {
            for (int node : list.get(i)) {
                sb.append(node).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}