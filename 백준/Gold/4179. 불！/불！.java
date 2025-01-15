import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int R;
    private static int C;

    // 벽은 -2, 빈 공간은 -1, 0 이상의 정수는 불이 발생하는 시간
    private static int[][] map;
    private static int[] person;

    private static ArrayDeque<int[]> fires;

    // 상하좌우
    private static int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static boolean isValidCoord(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    // 불이 몇 초부터 발생하는지 저장
    private static void spreadFire() {
        while (!fires.isEmpty()) {
            int[] cur = fires.pollFirst();
            int r = cur[0];
            int c = cur[1];
            int time = cur[2];

            if (!isValidCoord(r, c)) {
                continue;
            }

            // 이미 불이 존재하면(방문했으면)
            if (map[r][c] >= 0) {
                continue;
            }

            // 벽이면
            if (map[r][c] == -2) {
                continue;
            }

            map[r][c] = time;

            // 상하좌우로 불 확산
            for (int[] move : moves) {
                int dr = r + move[0];
                int dc = c + move[1];

                fires.addLast(new int[]{dr, dc, time + 1});
            }
        }
    }

    private static int BFS() {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];

        dq.addLast(new int[] { person[0], person[1], 0 });

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int r = cur[0];
            int c = cur[1];
            int time = cur[2];

            // 도착했는지 여부 확인
            if (!isValidCoord(r, c)) {
                return time;
            }

            // 불인지 확인
            if (map[r][c] != -1 && map[r][c] <= time) {
                continue;
            }

            // 벽인지 확인
            if (map[r][c] == -2) {
                continue;
            }

            // 이미 방문했는지 확인
            if (visited[r][c]) {
                continue;
            }

            // 방문 처리
            visited[r][c] = true;

            for (int[] move : moves) {
                int dr = move[0] + r;
                int dc = move[1] + c;

                dq.addLast(new int[] {dr, dc, time + 1});
            }
        }

        return -1;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        map = new int[R][C];
        person = new int[2];
        fires = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                char input = row[j].charAt(0);

                if (input == 'F') {
                    map[i][j] = -1;
                    // 불의 위치 저장
                    fires.add(new int[]{i, j, 0});
                } else if (input == '#') {
                    map[i][j] = -2;
                } else if (input == '.') {
                    map[i][j] = -1;
                } else {
                    map[i][j] = -1;
                    // 사람의 초기 위치 저장
                    person[0] = i;
                    person[1] = j;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        spreadFire();
        int time = BFS();
        if (time > 0) {
            System.out.println(time);
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }
}
