import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        char[] inputs = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        int num = 0;
        int answer = 0;

        for (char c : inputs) {
            if (c == '(') {
                num++;
            } else {
                num--;
                if (stack.peek() == '(') {
                    answer += num;
                } else {
                    answer++;
                }
            }
            stack.push(c);
        }

        System.out.println(answer);
    }
}