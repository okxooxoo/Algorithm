import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 아이들 수
    private static int M; // 친구 관계 수
    private static int K; // 공명하기 위한 최소 아이의 수(K명 미만으로 뺏어야 함)

    private static int[] parents;
    private static HashMap<Integer, Integer> groups; // <집합의 대표자, 집합의 캔디 수>

    private static int[] candies;

    private static void initSet() {
        parents = new int[N + 1];
        Arrays.fill(parents, -1);
    }

    private static int findSet(int a) {
        if (parents[a] < 0) {
            return a;
        }

        return parents[a] = findSet(parents[a]);
    }

    private static boolean isSameSet(int a, int b) {
        return findSet(a) == findSet(b);
    }

    private static void union(int a, int b) {
        if (isSameSet(a, b)) {
            return;
        }

        int aRoot = findSet(a);
        int bRoot = findSet(b);

        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
    }

    private static int solution() {
        int[] dp = new int[K];

        for (Integer key : groups.keySet()) {
            int childrenNum = -parents[key]; // 해당 그룹에 속한 아이들 수
            int candiesNum = groups.get(key); // 해당 그룹의 캔디 수

            for (int k = K - 1; k >= childrenNum; k--) {
                dp[k] = Math.max(dp[k], candiesNum + dp[k - childrenNum]);
            }
        }

        return dp[K - 1];
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        candies = new int[N + 1];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candies[i] = Integer.parseInt(tokens.nextToken());
        }

        initSet();

        for (int i = 1; i <= M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());

            union(a, b);
        }

        // 집합별 캔디 수 기록
        groups = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            int parent = findSet(i);
            groups.put(parent, candies[i] + groups.getOrDefault(parent, 0));
        }

    }

    public static void main(String[] args) throws IOException {
        init();
        int answer = solution();
        System.out.println(answer);
    }
}