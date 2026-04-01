import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 줄 수
    private static int P; // 프랫 수

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        P = Integer.parseInt(tokens.nextToken());

        ArrayList<Stack<Integer>> stacks = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            stacks.add(new Stack<>());
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());
            while (!stacks.get(x).isEmpty() && stacks.get(x).peek() > y) {
                stacks.get(x).pop();
                cnt++;
            }

            if (!stacks.get(x).isEmpty() && stacks.get(x).peek() == y) continue;

            stacks.get(x).push(y);
            cnt++;
        }

        System.out.println(cnt);
    }
}