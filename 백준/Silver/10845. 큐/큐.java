import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().split(" ");

            if (tokens[0].equals("push")) {
                q.addLast(Integer.parseInt(tokens[1]));
            } else if (tokens[0].equals("pop")) {
                sb.append(!q.isEmpty() ? q.pollFirst() : -1).append("\n");
            } else if (tokens[0].equals("size")) {
                sb.append(q.size()).append("\n");
            } else if (tokens[0].equals("empty")) {
                sb.append(q.isEmpty() ? 1 : 0).append("\n");
            } else if (tokens[0].equals("front")) {
                sb.append(!q.isEmpty() ? q.peekFirst() : -1).append("\n");
            } else if (tokens[0].equals("back")) {
                sb.append(!q.isEmpty() ? q.peekLast() : -1).append("\n");
            }
        }

        System.out.println(sb);
    }
}