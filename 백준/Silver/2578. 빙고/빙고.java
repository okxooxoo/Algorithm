import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int[][] bingo; // 각 숫자가 어느 행과 열에 위치하는지에 대한 정보
    private static boolean[][] checked; // 불려진 숫자 체크

    private static boolean isRowBingo(int row) {
        for (int c = 0; c < 5; c++) {
            if (!checked[row][c]) return false;
        }

        return true;
    }

    private static boolean isColumnBingo(int col) {
        for (int r = 0; r < 5; r++) {
            if (!checked[r][col]) return false;
        }

        return true;
    }

    private static boolean isLeftDiagonalBingo() {
        for (int i = 0; i < 5; i++) {
            if (!checked[i][i]) return false;
        }

        return true;
    }

    private static boolean isRightDiagonalBingo() {
        for (int i = 0; i < 5; i++) {
            if (!checked[i][5 - i - 1]) return false;
        }

        return true;
    }

    private static int calcBingoCount() {
        int bingoCnt = 0;

        for (int i = 0; i < 5; i++) {
            if (isRowBingo(i)) bingoCnt++;
        }

        for (int i = 0; i < 5; i++) {
            if (isColumnBingo(i)) bingoCnt++;
        }

        if (isLeftDiagonalBingo()) bingoCnt++;
        if (isRightDiagonalBingo()) bingoCnt++;

        return bingoCnt;
    }

    private static int solve() throws IOException {
        int answer = 0;

        for (int i = 0; i < 5; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                answer++;

                int num = Integer.parseInt(tokens.nextToken());
                int row = bingo[num][0];
                int col = bingo[num][1];
                checked[row][col] = true;

                if (calcBingoCount() >= 3) {
                    return answer;
                }
            }
        }

        return answer;
    }

    private static void init() throws IOException {
        bingo = new int[26][2];
        checked = new boolean[5][5];

        for (int i = 0; i < 5; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int num = Integer.parseInt(tokens.nextToken());
                bingo[num][0] = i;
                bingo[num][1] = j;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }
}