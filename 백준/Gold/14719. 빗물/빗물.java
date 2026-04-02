import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int H;
    private static int W;
    private static int[][] map;

    private static void simulation() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] != 0) continue;

                int left = -1;
                int right = -1;
                // 왼쪽에 블록이 있는지 탐색
                for (int k = j; k >= 0; k--) {
                    if (map[i][k] == 1) {
                        left = k;
                        break;
                    }
                }

                // 오른쪽에 블록이 있는지 탐색
                for (int k = j; k < W; k++) {
                    if (map[i][k] == 1) {
                        right = k;
                        break;
                    }
                }

                // 고일 수 있으면 빗물 채우기
                if (left != -1 && right != -1) {
                    for (int k = left + 1; k < right; k++) {
                        map[i][k] = 2;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        H = Integer.parseInt(tokens.nextToken());
        W = Integer.parseInt(tokens.nextToken());

        map = new int[H][W];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int maxH = Integer.parseInt(tokens.nextToken());
            for (int j = H - 1; j >= H - maxH; j--) {
                map[j][i] = 1;
            }
        }

        simulation();

        int cnt = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 2) cnt++;
            }
        }
        System.out.println(cnt);
    }
}