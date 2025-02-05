import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int n; // 트리 노드의 개수
    private static int[] inOrder;
    private static int[] postOrder;

    // 분할 정복
    public static void DivideConquer(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) return;

        // 현재 트리의 루트 노드 값
        int root = postOrder[postEnd];
        sb.append(root).append(" ");

        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == root) {
                int len = i - inStart;

                DivideConquer(inStart, i - 1, postStart, postStart + len - 1);
                DivideConquer(i + 1, inEnd, postStart + len, postEnd - 1);
            }
        }
    }

    public static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        inOrder = new int[n + 1];
        postOrder = new int[n + 1];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            inOrder[i] = Integer.parseInt(tokens.nextToken());
        }

        tokens = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            postOrder[i] = Integer.parseInt(tokens.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        DivideConquer(1, n, 1, n);
        System.out.println(sb);
    }
}