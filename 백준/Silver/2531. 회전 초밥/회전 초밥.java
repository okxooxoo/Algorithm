import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 벨트에 놓인 접시의 수
    private static int d; // 초밥의 가짓수
    private static int k; // 연속해서 먹는 수
    private static int c; // 쿠폰 번호

    private static int[] arr;
    private static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        d = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());
        c = Integer.parseInt(tokens.nextToken());

        arr = new int[N];
        map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            map.put(arr[i], 0);
        }

        map.put(c, 1);

        int left = 0;
        int right = k - 1;
        int cnt = 1;
        int ans;

        for (int i = left; i <= right; i++) {
            if (map.get(arr[i]) == 0) {
                cnt++;
            }
            map.put(arr[i], map.get(arr[i]) + 1);
        }

        ans = cnt;

        while (left < N) {
            map.put(arr[left], map.get(arr[left]) - 1);
            if (map.get(arr[left]) == 0) cnt--;

            left++;
            right = (right + 1) % N;

            if (map.get(arr[right]) == 0) cnt++;
            map.put(arr[right], map.get(arr[right]) + 1);

            ans = Math.max(ans, cnt);
        }

        System.out.println(ans);
    }
}