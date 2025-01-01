import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 스태프의 수
    private static int M; // 풍선의 개수

    private static int[] times; // 각 스태프가 풍선을 만드는 시간
    private static long maxTime; // 풍선을 만드는 데 최대로 걸릴 수 있는 시간

    // 해당 시간 동안 M개의 풍선을 만들 수 있는지 여부 반환
    private static boolean canMakeBalloonsInTime(long time) {
        long count = 0;

        for (int i = 0; i < N; i++) {
            count += time / times[i];
        }

        return count >= M;
    }

    private static long binarySearch() {
        long answer = 0;

        long left = 1;
        long right = maxTime;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (canMakeBalloonsInTime(mid)) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        times = new int[N];
        int max = 0;

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(tokens.nextToken());
            max = Math.max(max, times[i]);
        }

        maxTime = (long)max * M;
    }

    public static void main(String[] args) throws IOException {
        init();
        long answer = binarySearch();
        System.out.println(answer);
    }
}