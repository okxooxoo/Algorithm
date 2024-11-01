import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N; // 노트북 가로 길이
    private static int M; // 노트북 세로 길이
    private static int K; // 스티커 개수

    private static int[][] notebook;
    private static Sticker[] stickers;

    private static void solution() {
        for (Sticker sticker : stickers) {
            sticker.attachAndRotate();
        }
    }

    // 노트북에 붙인 스티커 개수 세기
    private static int count() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (notebook[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        notebook = new int[N][M];
        stickers = new Sticker[K];

        for (int i = 0; i < K; i++) {
            tokens = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(tokens.nextToken());
            int C = Integer.parseInt(tokens.nextToken());

            stickers[i] = new Sticker(R, C);

            for (int r = 0; r < R; r++) {
                tokens = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    stickers[i].matrix[r][c] = Integer.parseInt(tokens.nextToken());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(count());
    }

    static class Sticker {
        int R;
        int C;
        int[][] matrix;

        Sticker(int R, int C) {
            this.R = R;
            this.C = C;
            this.matrix = new int[R][C];
        }

        // 시계 방향으로 90도 회전
        void rotateRight() {
            int[][] newMatrix = new int[C][R];

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    newMatrix[c][R - 1 - r] = matrix[r][c];
                }
            }

            int temp = this.R;
            this.R = C;
            this.C = temp;

            // 이차원 배열 복사
            this.matrix = new int[R][C];

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    matrix[r][c] = newMatrix[r][c];
                }
            }
        }

        // 각도를 돌려가며 스티커 붙이기 시도
        void attachAndRotate() {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        if (attach(j, k)) {
                            return;
                        }
                    }
                }
                rotateRight();
            }
        }

        // 노트북에 스티커 붙이기
        boolean attach(int startR, int startC) {
            int[][] copyNotebook = new int[N][M];

            // 노트북 이차원 배열 복사
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    copyNotebook[i][j] = notebook[i][j];
                }
            }

            // 노트북에 스티커 붙이기 시도
            for (int i = startR; i < startR + R; i++) {
                for (int j = startC; j < startC + C; j++) {
                    if (i >= N || j >= M) {
                        return false;
                    }

                    if (matrix[i - startR][j - startC] == 1) {
                        if (copyNotebook[i][j] == 1) {
                            // 이미 스티커가 붙여져 있으면 false 리턴
                            return false;
                        }
                        copyNotebook[i][j] = 1;
                    }
                }
            }

            // 스티커를 붙일 수 있으면 원본 노트북 배열에 반영
            for (int i = startR; i < startR + R; i++) {
                for (int j = startC; j < startC + C; j++) {
                    if (copyNotebook[i][j] == 1) {
                        notebook[i][j] = 1;
                    }
                }
            }

            return true;
        }
    }
}