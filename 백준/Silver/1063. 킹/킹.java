import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb;

    private static int[] kingPos;
    private static int[] stonePos;
    private static int[][] matrix;
    private static String[] moves;

    private static void move(int dr, int dc) {
        int r = kingPos[0];
        int c = kingPos[1];

        int nr = r + dr;
        int nc = c + dc;

        // 킹이 범위를 벗어나면 움직이지 않음
        if (nr < 0 || nr >= 8 || nc < 0 || nc >= 8) {
            return;
        }

        // 움직일 곳에 돌이 있으면
        if (matrix[nr][nc] == 2) {
            // 돌이 범위를 벗어나면 움직이지 않음
            if (nr + dr < 0 || nr + dr >= 8 || nc + dc < 0 || nc + dc >= 8) {
                return;
            }

            matrix[nr + dr][nc + dc] = 2;
            stonePos[0] = nr + dr;
            stonePos[1] = nc + dc;
        }

        matrix[nr][nc] = 1;
        matrix[r][c] = 0;
        kingPos[0] = nr;
        kingPos[1] = nc;
    }

    private static void simulation() {
        for (String move : moves) {
            switch (move) {
                case "R":
                    move(0, 1);
                    break;
                case "L":
                    move(0, -1);
                    break;
                case "B":
                    move(1, 0);
                    break;
                case "T":
                    move(-1, 0);
                    break;
                case "RT":
                    move(-1, 1);
                    break;
                case "LT":
                    move(-1, -1);
                    break;
                case "RB":
                    move(1, 1);
                    break;
                case "LB":
                    move(1, -1);
                    break;
            }
        }
    }

    private static void output() {
        sb = new StringBuilder();
        sb.append((char)('A' + kingPos[1])).append(8 - kingPos[0]).append("\n");
        sb.append((char)('A' + stonePos[1])).append(8 - stonePos[0]).append("\n");
        System.out.println(sb);
    }

    private static void init() throws IOException {
        matrix = new int[8][8];
        kingPos = new int[2];
        stonePos = new int[2];

        tokens = new StringTokenizer(br.readLine());
        String king = tokens.nextToken();
        int col = king.charAt(0) - 'A';
        int row = 8 - (king.charAt(1) - '0');

        matrix[row][col] = 1; // 킹의 위치를 1로 표시
        kingPos[0] = row;
        kingPos[1] = col;

        String stone = tokens.nextToken();
        col = stone.charAt(0) - 'A';
        row = 8 - (stone.charAt(1) - '0');

        matrix[row][col] = 2; // 돌의 위치를 2로 표시
        stonePos[0] = row;
        stonePos[1] = col;

        int moveCnt = Integer.parseInt(tokens.nextToken());
        moves = new String[moveCnt];

        for (int i = 0; i < moveCnt; i++) {
            moves[i] = br.readLine();
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        simulation();
        output();
    }
}