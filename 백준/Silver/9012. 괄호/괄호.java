import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb;

    private static int N; // 얼음 양동이 개수

    private static boolean isVPS(char[] str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str) {
            if (c == '(') {
                stack.push('(');
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            char[] inputs = br.readLine().toCharArray();
            if (isVPS(inputs)) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);
    }
}