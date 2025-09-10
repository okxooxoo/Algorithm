import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 행의 개수
    private static int M; // 열의 개수

    private static int[][] matrix;
    private static int candidate;

    private static boolean isValid(int r, int c, int distance) {
        if (matrix[r][c] != matrix[r + distance][c]) return false;
        if (matrix[r][c] != matrix[r][c + distance]) return false;
        if (matrix[r][c] != matrix[r + distance][c + distance]) return false;
        return true;
    }

    private static int solution() {
        while (candidate > 0) {
            for (int i = 0; i < N - candidate + 1; i++) {
                for (int j = 0; j < M - candidate + 1; j++) {
                    if (isValid(i, j, candidate - 1)) return candidate * candidate;
                }
            }

            candidate--;
        }

        return 1;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        candidate = Math.min(N, M); // 정사각형 크기 후보 중 가장 큰 값

        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = line.charAt(j) - '0';
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }
}
