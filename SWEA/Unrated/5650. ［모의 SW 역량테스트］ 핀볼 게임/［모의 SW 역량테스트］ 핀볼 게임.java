import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    // 상, 우, 하, 좌
    private static final int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int N;
    private static PinBall pinBall;
    private static int[][] map;
    private static ArrayList<Wormhole> wormholes;

    private static int solve() {
        int mx = 0; // 최대 점수
        int score = 0;

        // 모든 경우의 수 따지기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 빈 공간이 아니면 핀볼을 둘 수 없음
                if (map[i][j] != 0) continue;

                // 4 방향에 대하여 시뮬레이션
                for (int k = 0; k < 4; k++) {
                    pinBall = new PinBall(i, j, k);
                    score = simulation();
                    mx = Math.max(mx, score);
                }
            }
        }

        return mx;
    }

    private static int simulation() {
        int score = 0;
        pinBall.move();

        while (true) {
            // 벽에 부딪히면
            if (pinBall.isWall()) {
                pinBall.reverse();
                pinBall.move();
                score++;
            // 시작점으로 돌아오면
            } else if (pinBall.isStart()) {
                break;
            // 블랙홀을 만나면
            } else if (map[pinBall.r][pinBall.c] == -1) {
                break;
            // 빈 공간이면
            } else if (map[pinBall.r][pinBall.c] == 0) {
                pinBall.move();
            // 블록을 만나면
            } else if (map[pinBall.r][pinBall.c] == 1) {
                if (pinBall.direction == 2) {
                    pinBall.left();
                } else if (pinBall.direction == 3) {
                    pinBall.right();
                } else {
                    pinBall.reverse();
                }
                pinBall.move();
                score++;
            } else if (map[pinBall.r][pinBall.c] == 2) {
                if (pinBall.direction == 3) {
                    pinBall.left();
                } else if (pinBall.direction == 0) {
                    pinBall.right();
                } else {
                    pinBall.reverse();
                }
                pinBall.move();
                score++;
            } else if (map[pinBall.r][pinBall.c] == 3) {
                if (pinBall.direction == 0) {
                    pinBall.left();
                } else if (pinBall.direction == 1) {
                    pinBall.right();
                } else {
                    pinBall.reverse();
                }
                pinBall.move();
                score++;
            } else if (map[pinBall.r][pinBall.c] == 4) {
                if (pinBall.direction == 1) {
                    pinBall.left();
                } else if (pinBall.direction == 2) {
                    pinBall.right();
                } else {
                    pinBall.reverse();
                }
                pinBall.move();
                score++;
            } else if (map[pinBall.r][pinBall.c] == 5) {
                pinBall.reverse();
                pinBall.move();
                score++;
            // 웜홀을 만나면
            } else if (6 <= map[pinBall.r][pinBall.c]) {
                pinBall.teleport(pinBall.r, pinBall.c, map[pinBall.r][pinBall.c]);
                pinBall.move();
            }
        }

//        System.out.println(score);
        return score;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];
        wormholes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                // 웜홀이면 웜홀 객체 생성
                if (6 <= map[i][j] && map[i][j] <= 10) {
                    wormholes.add(new Wormhole(i, j, map[i][j]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            init();
            sb.append("#").append(t).append(" ").append(solve()).append("\n");
        }
        System.out.println(sb);
    }

    static class PinBall {
        int startR;
        int startC;
        int r;
        int c;
        int direction;
        int score;

        PinBall(int startR, int startC, int direction) {
            this.startR = startR;
            this.startC = startC;
            this.r = startR;
            this.c = startC;
            this.direction = direction;
            this.score = 0;
        }

        // 시작 좌표로 돌아왔는지 판단
        boolean isStart() {
            if (r == startR && c == startC) {
                return true;
            }
            return false;
        }

        boolean isWall() {
            if (r < 0 || c < 0 || r >= N || c >= N) {
                return true;
            }
            return false;
        }

        void move() {
            this.r += delta[direction][0];
            this.c += delta[direction][1];
        }

        // 반대 방향으로 전환 후 이동
        void reverse() {
            direction = (direction + 2) % 4;
        }

        // 왼쪽으로 각도 회전
        void left() {
            direction = (direction + 3) % 4;
        }

        // 오른쪽으로 각도 회전
        void right() {
            direction = (direction + 1) % 4;
        }

        // 웜홀이면 연결된 웜홀로 이동
        void teleport(int r, int c, int num) {
            for (Wormhole w : wormholes) {
                if (w.num == num) {
                    if (w.r == r && w.c == c) {
                        continue;
                    }

                    this.r = w.r;
                    this.c = w.c;
                    break;
                }
            }
        }
    }

    static class Wormhole {
        int r;
        int c;
        int num;

        Wormhole(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }
}