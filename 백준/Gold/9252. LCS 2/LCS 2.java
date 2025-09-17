import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static char[] A;
    private static char[] B;
    private static int[][] dp;

    private static int answerLength;
    private static char[] answer;

    private static void solution() {
        // LCS 길이 구하기
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        answerLength = dp[A.length][B.length];
        answer = new char[answerLength];

        // LCS 구하기
        int r = A.length;
        int c = B.length;
        int index = 0;

        while (index < answerLength) {
            if (dp[r][c] == dp[r - 1][c]) {
                r--;
            } else if (dp[r][c] == dp[r][c - 1]) {
                c--;
            } else {
                answer[index++] = A[r - 1];
                r--;
                c--;
            }
        }

        // reverse
        for (int i = 0; i < answer.length / 2; i++) {
            char temp = answer[i];
            answer[i] = answer[answer.length - 1 - i];
            answer[answer.length - 1 - i] = temp;
        }
    }

    private static void init() throws IOException {
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();
        dp = new int[A.length + 1][B.length + 1];
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        bw.write(answerLength + "\n");
        bw.write(new String(answer) + "\n");
        bw.flush();
        bw.close();
    }
}
