import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[] bulbs;
    private static int answer;

    private static int[][] deltas = {{0,0}, {1,0}, {-1,0}, {0,1}, {0,-1}};

    private static void init() throws IOException {
        bulbs = new int[10];
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < 10; j++) {
                if (row[j].equals("O")) {
                    bulbs[i] |= (1 << j);
                }
            }
        }
    }

    private static int[] switchBulb(int row, int col, int[] bulbs) {
        int[] newBulbs = Arrays.copyOf(bulbs, bulbs.length);

        for (int[] delta : deltas) {
            int nr = row + delta[0];
            int nc = col + delta[1];

            if (nr >= 0 && nr < 10 && nc >= 0 && nc < 10) {
                newBulbs[nr] ^= (1 << nc);
            }
        }

        return newBulbs;
    }

    private static int calcSwitchCount(int[] bulbs) {
        int switchCount = 0;

        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((bulbs[i-1] & (1 << j)) != 0) {
                    bulbs = switchBulb(i, j, bulbs);
                    switchCount++;
                }
            }
        }

        if (bulbs[9] == 0) return switchCount;
        return -1;
    }

    /**
     * 첫 번째 행에서 전구를 스위치하는 모든 경우의 수 구하기
     * 첫 번째 행만 결정되면 그 이후 스위치 온오프 여부는 고정되어 있음
     * @param depth
     * @param bulbs 현재 전구 상태
     * @param count 지금까지 누른 개수
     */
    private static void solution(int depth, int[] bulbs, int count) {
        if (depth == 10) {
            int switchCount = calcSwitchCount(bulbs);
            if (switchCount == -1) return;

            answer = Math.min(answer, switchCount + count);
            return;
        }

        // 현재 전구를 스위치하지 않고 넘어감
        solution(depth + 1, bulbs, count);

        // 현재 전구를 스위치함
        int[] newBulbs = switchBulb(0, depth, bulbs);
        solution(depth + 1, newBulbs, count + 1);
    }

    public static void main(String[] args) throws IOException {
        init();
        solution(0, bulbs, 0);
        System.out.println(answer);
    }
}