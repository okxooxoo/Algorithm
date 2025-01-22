import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[][] map;
    // 구간합
    private static int[][] sectionSum;
    private static int maxValue;

    // 모든 구간에 대한 조합 구하기
    private static void makeCombination() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {

                for (int k = 0; k < N; k++) {
                    int endR = i + k;
                    int endC = j + k;

                    if (endR > N || endC > N) break;

                    int value = calcSectionSum(i, j, endR, endC);
                    maxValue = Math.max(maxValue, value);

                }
            }
        }
    }

    private static int calcSectionSum(int startR, int startC, int endR, int endC) {
        return sectionSum[endR][endC] - sectionSum[startR - 1][endC] - sectionSum[endR][startC - 1] + sectionSum[startR - 1][startC - 1];
    }

    private static void initSectionSum() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sectionSum[i][j] = map[i][j] + sectionSum[i - 1][j] + sectionSum[i][j - 1] - sectionSum[i - 1][j - 1];
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        sectionSum = new int[N + 1][N + 1];

        maxValue = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        initSectionSum();
        makeCombination();
        System.out.println(maxValue);
    }
}