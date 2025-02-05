import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static ArrayList<Integer> steps;

    private static final int SAME_SCORE = 1; // 같은 지점 밟을 때 점수
    private static final int CENTER_SCORE = 2; // 중앙에서 다른 지점 밟을 때 점수
    private static final int ADJOIN_SCORE = 3; // 인접한 지점 밟을 때 점수
    private static final int OPPOSITE_SCORE = 4; // 반대편 지점 밟을 때 점수

    // [현재 스텝 순서, 왼발 위치, 오른발 위치]
    private static int[][][] dp;

    private static int getScore(int prev, int next) {
        if (prev == next) return SAME_SCORE;
        if (prev == 0) return CENTER_SCORE;
        if ((prev + next) % 2 == 1) return ADJOIN_SCORE;
        return OPPOSITE_SCORE;
    }

    private static int dfs(int index, int left, int right) {
        if (index == steps.size()) return 0;
        if (dp[index][left][right] != 0) return dp[index][left][right];

        // 왼발 움직일 때의 점수
        int leftScore = dfs(index + 1, steps.get(index), right) + getScore(left, steps.get(index));
        // 오른발 움직일 때의 점수
        int rightScore = dfs(index + 1, left, steps.get(index)) + getScore(right, steps.get(index));;

        dp[index][left][right] = Math.min(leftScore, rightScore);
        return dp[index][left][right];
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        steps = new ArrayList<>();

        while (tokens.hasMoreTokens()) {
            int step = Integer.parseInt(tokens.nextToken());
            if (step == 0) break;
            steps.add(step);
        }

        int len = steps.size();
        dp = new int[len][5][5];
    }

    public static void main(String[] args) throws IOException {
        init();
        int answer = dfs(0, 0, 0);
        System.out.println(answer);
    }
}