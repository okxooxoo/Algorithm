import java.util.*;

class Solution {
    private static int[] apeach;
    private static int[] lion;
    private static int[] answer;
    private static int maxDiff;

    // 라이언과 어피치 점수 차이 계산
    private static int calcDiff() {
        int apeachScore = 0;
        int lionScore = 0;
        for (int i = 0; i <= 10; i++) {
            if (apeach[i] == 0 && lion[i] == 0) continue;
            if (lion[i] > apeach[i]) lionScore += 10 - i;
            else apeachScore += 10 - i;
        }
        return lionScore - apeachScore;
    }

    private static void dfs(int depth, int curArrow) {
        if (depth == 11) { // 모든 점수대 탐색 완료
            int diff = calcDiff();
            if (diff <= 0) return; // 라이언이 이길 수 없는 경우
            if (diff > maxDiff) {
                maxDiff = diff;
                answer = Arrays.copyOf(lion, 11);
            } else if (diff == maxDiff) {
                // 낮은 점수 우선 비교
                for (int i = 10; i >= 0; i--) {
                    if (lion[i] > answer[i]) {
                        answer = Arrays.copyOf(lion, 11);
                        break;
                    } else if (lion[i] < answer[i]) {
                        break;
                    }
                }
            }
            return;
        }

        // 남은 화살 모두 0점에 몰아넣는 경우
        if (depth == 10) {
            lion[10] = curArrow;
            dfs(depth + 1, 0);
            lion[10] = 0;
            return;
        }

        // 1) 점수 가져가는 경우
        int need = apeach[depth] + 1;
        if (curArrow >= need) {
            lion[depth] = need;
            dfs(depth + 1, curArrow - need);
            lion[depth] = 0; // 백트래킹
        }

        // 2) 점수 포기하는 경우
        dfs(depth + 1, curArrow);
    }

    public int[] solution(int n, int[] info) {
        apeach = Arrays.copyOf(info, 11);
        lion = new int[11];
        answer = new int[11];
        maxDiff = 0;

        dfs(0, n);

        if (maxDiff == 0) return new int[]{-1};
        return answer;
    }
}