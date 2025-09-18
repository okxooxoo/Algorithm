import java.io.*;
import java.util.Stack;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int solution(char[] data) {
        int count = 0;
        Stack<Character> stack = new Stack<>();

        for (char c : data) {
            if (c == '{') {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    stack.push('{');
                    count++;
                }
            }
        }

        count += stack.size() / 2;

        return count;
    }

    public static void main(String[] args) throws IOException {
        int index = 1;

        while (true) {
            char[] data = br.readLine().toCharArray();
            // 종료 조건
            if (data[0] == '-') {
                break;
            }

            int num = solution(data);

            bw.write(index++ + ". " + num + "\n");
        }
        bw.flush();
        bw.close();
    }
}
