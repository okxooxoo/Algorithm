import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int M;

    private static int[][] map;
    private static Robot robot;
    private static int count; // 청소한 영역 개수

    // 북, 동, 남, 서
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static void simulation() {
        while (true) {
            robot.clean();

            if (robot.canNearClean()) {
                robot.rotate();
                robot.forward();
            } else {
                if (!robot.backward()) {
                    break;
                }
            }
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];

        tokens = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(tokens.nextToken());
        int c = Integer.parseInt(tokens.nextToken());
        int direction = Integer.parseInt(tokens.nextToken());

        robot = new Robot(r, c, direction);

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
    }


    public static void main(String[] args) throws IOException {
        init();
        simulation();
        System.out.println(count);
    }

    static class Robot {
        int r;
        int c;
        int direction;

        Robot(int r, int c, int direction) {
            this.r = r;
            this.c = c;
            this.direction = direction;
        }

        // 반시계 방향으로 90도 회전
        void rotate() {
            this.direction = (this.direction + 3) % 4;
        }

        // 전진할 수 있으면 전진
        void forward() {
            int dr = this.r + moves[direction][0];
            int dc = this.c + moves[direction][1];

            if (map[dr][dc] == 0) {
                this.r = dr;
                this.c = dc;
            }
        }

        // 후진할 수 있으면 후진
        boolean backward() {
            int dr = this.r - moves[direction][0];
            int dc = this.c - moves[direction][1];

            if (map[dr][dc] != 1) {
                this.r = dr;
                this.c = dc;

                return true;
            }
            return false;
        }

        // 주변을 청소할 수 있는지 여부
        boolean canNearClean() {
            for (int[] move : moves) {
                int dr = r + move[0];
                int dc = c + move[1];

                if (map[dr][dc] == 0) {
                    return true;
                }
            }

            return false;
        }

        void clean() {
            if (map[r][c] == 0) {
                map[r][c] = 2;
                count++;
            }
        }
    }
}