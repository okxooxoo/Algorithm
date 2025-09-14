import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static char[] board;

    public static String solution() {
        char[] answer = new char[board.length];
        int index = 0;
        int count = 0; // 연속된 X의 개수

        for (int i = 0; i < board.length; i++) {
            if (board[i] == 'X') {
                count++;
            } else {
                // 폴리오미노로 덮을 수 없으면 -1 출력
                if (count % 2 == 1) {
                    return "-1";
                } else {
                    // 폴리오미노로 덮기
                    int a = count / 4 * 4;
                    int b = count % 4;
                    for (int j = 0; j < a; j++) {
                        answer[index++] = 'A';
                    }
                    for (int j = 0; j < b; j++) {
                        answer[index++] = 'B';
                    }

                    answer[index++] = '.';
                    count = 0;
                }
            }
        }


        // 폴리오미노로 덮을 수 없으면 -1 출력
        if (count % 2 == 1) {
            return "-1";
        } else {
            // 폴리오미노로 덮기
            int a = count / 4 * 4;
            int b = count % 4;
            for (int j = 0; j < a; j++) {
                answer[index++] = 'A';
            }
            for (int j = 0; j < b; j++) {
                answer[index++] = 'B';
            }

        }

        return new String(answer);
    }

    public static void main(String[] args) throws IOException {
        board = br.readLine().toCharArray();
        bw.write(solution());
        bw.flush();
        bw.close();
    }
}
