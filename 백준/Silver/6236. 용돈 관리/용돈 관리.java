import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N; // 일
    private static int M; // 출금 횟수
    private static int[] amounts; // 각 일자별 사용할 돈

    private static boolean simulation(int money) {

        int count = 0;
        int curMoney = 0;

        for (int i = 0; i < N; i++) {
            if (money < amounts[i]) {
                return false;
            }

            if (curMoney < amounts[i]) {
                count++;
                curMoney = money;
            }
            curMoney -= amounts[i];
        }

        return count <= M;
    }

    private static int binarySearch(int low, int high) {
        int answer = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (simulation(mid)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        amounts = new int[N];
        int low = Integer.MAX_VALUE;
        int high = 0;

        for (int i = 0; i < N; i++) {
            amounts[i] = Integer.parseInt(br.readLine());
            low = Math.min(low, amounts[i]);
            high += amounts[i];
        }

        System.out.println(binarySearch(low, high));
    }
}