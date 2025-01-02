import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int M;

    private static int[][] map;
    private static int[][] cmd;

    private static ArrayList<Cloud> clouds;

    private static int[][] deltas = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};

    private static void simulation() {
        boolean[][] disable; // 구름이 사라진 곳

        for (int i = 0; i < M; i++) {
            disable = new boolean[N][N];

            int d = cmd[i][0]; // 방향
            int s = cmd[i][1]; // 거리

            // 구름 이동
            for (Cloud cloud : clouds) {
                cloud.move(d, s);
            }

            // 비 내리기 (+1씩 증가)
            for (Cloud cloud : clouds) {
                map[cloud.r][cloud.c]++;
                // 구름이 사라진 곳 표시
                disable[cloud.r][cloud.c] = true;
            }

            // 구름이 있었던 칸 물복사
            for (Cloud cloud : clouds) {
                cloud.copyWater();
            }

            // 새로운 구름 생성
            clouds = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] >= 2 && !disable[r][c]) {
                        clouds.add(new Cloud(r, c));
                        map[r][c] -= 2;
                    }
                }
            }
        }
    }

    private static int getWaterSum() {
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += map[i][j];
            }
        }

        return sum;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        cmd = new int[M][2];
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            cmd[i][0] = Integer.parseInt(tokens.nextToken());
            cmd[i][1] = Integer.parseInt(tokens.nextToken());
        }

        // 초기 구름 생성
        clouds = new ArrayList<>();
        clouds.add(new Cloud(N-1, 0));
        clouds.add(new Cloud(N-1, 1));
        clouds.add(new Cloud(N-2, 0));
        clouds.add(new Cloud(N-2, 1));
    }

    public static void main(String[] args) throws IOException {
        init();
        simulation();
        System.out.println(getWaterSum());
    }

    static class Cloud {
        int r;
        int c;

        Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }

        void move(int d) {
            this.r += deltas[d - 1][0];
            this.c += deltas[d - 1][1];

            if (this.r < 0) {
                this.r = N - 1;
            }

            if (this.c < 0) {
                this.c = N - 1;
            }

            if (this.r >= N) {
                this.r = 0;
            }

            if (this.c >= N) {
                this.c = 0;
            }
        }

        void move(int d, int s) {
            for (int i = 0; i < s; i++) {
                move(d);
            }
        }

        // 물복사
        void copyWater() {
            int count = 0;

            // 대각선 방향만 순회
            for (int i = 1; i < 8; i += 2) {
                int dr = this.r + deltas[i][0];
                int dc = this.c + deltas[i][1];

                if (dr < 0 || dc < 0 || dr >= N || dc >= N) {
                    continue;
                }

                if (map[dr][dc] > 0) {
                    count++;
                }
            }

            map[r][c] += count;
        }
    }
}