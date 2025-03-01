import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int R;
    private static int C;
    private static int T;

    private static int[][] room;
    private static int[] robotTop; // 로봇 상단 위치
    private static int[] robotBottom; // 로봇 하단 위치

    // 상 우 하 좌
    private static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static void solution() {
        int time = 0;

        while (time < T) {
            time++;

            int[][] copyRoom = new int[R][C];

            for (int i = 0; i < R; i++) {
                copyRoom[i] = Arrays.copyOf(room[i], room[i].length);
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (i == robotTop[0] && j == robotTop[1]) continue;
                    if (i == robotBottom[0] && j == robotBottom[1]) continue;

                    spread(i, j, copyRoom[i][j] / 5);
                }
            }

            purifyTop();
            purifyBottom();
        }
    }

    // 미세먼지 확산
    private static void spread(int r, int c, int dust) {
        int dustSum = 0;

        for (int[] delta : deltas) {
            int dr = r + delta[0];
            int dc = c + delta[1];

            if (isInvalidCoord(dr, dc)) continue;
            if (dr == robotTop[0] && dc == robotTop[1]) continue;
            if (dr == robotBottom[0] && dc == robotBottom[1]) continue;

            room[dr][dc] += dust;
            dustSum += dust;
        }

        room[r][c] -= dustSum;
    }

    // 공기청정기 정화
    private static void purifyTop() {
        int r = robotTop[0] - 1;
        int c = robotTop[1];
        int directionIdx = 0;

        while (!(r == robotTop[0] && c == robotTop[1])) {
            int oppDirectionIdx = (directionIdx + 2) % 4;
            int dr = r + deltas[oppDirectionIdx][0];
            int dc = c + deltas[oppDirectionIdx][1];

            // 먼지 이동
            room[dr][dc] += room[r][c];
            room[r][c] = 0;

            // 유효하지 않은 좌표면 방향 변경
            int nr = r + deltas[directionIdx][0];
            int nc = c + deltas[directionIdx][1];

            if (isInvalidCoord(nr, nc) || nr > robotTop[0]) {
                directionIdx = (directionIdx + 1) % 4;
            }

            // 다음 좌표로 이동
            nr = r + deltas[directionIdx][0];
            nc = c + deltas[directionIdx][1];
            r = nr;
            c = nc;
        }
    }

    private static void purifyBottom() {
        int r = robotBottom[0] + 1;
        int c = robotBottom[1];
        int directionIdx = 2;

        while (!(r == robotBottom[0] && c == robotBottom[1])) {
            int oppDirectionIdx = (directionIdx + 2) % 4;
            int dr = r + deltas[oppDirectionIdx][0];
            int dc = c + deltas[oppDirectionIdx][1];

            // 먼지 이동
            room[dr][dc] = room[r][c];
            room[r][c] = 0;

            // 유효하지 않은 좌표면 방향 변경
            int nr = r + deltas[directionIdx][0];
            int nc = c + deltas[directionIdx][1];

            if (isInvalidCoord(nr, nc) || nr < robotBottom[0]) {
                directionIdx = (directionIdx + 3) % 4;
            }

            // 다음 좌표로 이동
            nr = r + deltas[directionIdx][0];
            nc = c + deltas[directionIdx][1];
            r = nr;
            c = nc;
        }
    }

    private static boolean isInvalidCoord(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

    private static int getDustCount() {
        int dustCount = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (i == robotTop[0] && j == robotTop[1]) continue;
                if (i == robotBottom[0] && j == robotBottom[1]) continue;

                dustCount += room[i][j];
            }
        }

        return dustCount;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        T = Integer.parseInt(tokens.nextToken());

        room = new int[R][C];
        boolean findRobot = false;

        for (int i = 0; i < R; i++) {
            tokens = new StringTokenizer(br.readLine());

            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(tokens.nextToken());

                if (room[i][j] == -1) {
                    if (!findRobot) {
                        robotTop = new int[]{i, j};
                        robotBottom = new int[]{i + 1, j};
                        findRobot = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(getDustCount());
    }
}