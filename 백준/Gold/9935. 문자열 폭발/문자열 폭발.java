import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static char[] S;
    private static char[] pattern;

    private static void solution() {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < S.length; i++) {
            stack.push(S[i]);

            if (stack.size() >= pattern.length) {
                int count = 0;

                for (int j = 0; j < pattern.length; j++) {
                    if (stack.get(stack.size() - pattern.length + j) == pattern[j]) {
                        count++;
                    }
                }

                if (count == pattern.length) {
                    for (int j = 0; j < pattern.length; j++) {
                        stack.pop();
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            sb.append("FRULA");
        } else {
            for (char c : stack) {
                sb.append(c);
            }
        }
    }

    private static void init() throws IOException {
        String[] sInput = br.readLine().split("");

        S = new char[sInput.length];

        for (int i = 0; i < sInput.length; i++) {
            S[i] = sInput[i].charAt(0);
        }

        String[] pInput = br.readLine().split("");

        pattern = new char[pInput.length];

        for (int i = 0; i < pInput.length; i++) {
            pattern[i] = pInput[i].charAt(0);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(sb);
    }
}