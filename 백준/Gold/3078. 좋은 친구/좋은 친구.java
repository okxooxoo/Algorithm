import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int K;

    private static int[] names;
    private static HashMap<Integer, Integer> nameCounts;

    private static long solution() {
        long count = 0;
        int left = 0;
        int right = K;

        for (int i = left; i <= right; i++) {
            nameCounts.put(names[i], nameCounts.getOrDefault(names[i], 0) + 1);
        }

        while (left < right) {
            count += nameCounts.get(names[left]) - 1;
            nameCounts.put(names[left], nameCounts.get(names[left]) - 1);
            left++;

            if (right < N - 1) {
                right++;
                nameCounts.put(names[right], nameCounts.getOrDefault(names[right], 0) + 1);
            }
        }

        return count;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        names = new int[N];
        nameCounts = new HashMap<>();

        for (int i = 0; i < N; i++) {
            // 이름의 길이만 저장
            names[i] = br.readLine().length();
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        long answer = solution();
        System.out.println(answer);
    }
}