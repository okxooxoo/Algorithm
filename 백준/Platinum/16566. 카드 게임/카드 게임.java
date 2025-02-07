import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int M;
    private static int K;

    private static int[] myCards;
    private static int[] yourCards; // 내가 낸 카드 기록

    private static int[] parents;

    private static void initSet() {
        parents = new int[M + 1];

        for (int i = 0; i < M + 1; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int a) {
        if (parents[a] == a) {
            return a;
        }

        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) {
            return false;
        } else if (aRoot > bRoot) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }

        return true;
    }

    private static void solution() {
        initSet();

        for (int yourCard : yourCards) {
            int answer = binarySearch(yourCard);
            answer = findSet(answer);
            union(answer, answer + 1);

            sb.append(myCards[answer]).append("\n");
        }
    }

    private static int binarySearch(int yourCard) {
        int left = 0;
        int right = M - 1;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (myCards[mid] > yourCard) {
                answer = mid;
                right = mid - 1;
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
        K = Integer.parseInt(tokens.nextToken());

        myCards = new int[M];
        yourCards = new int[K];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            myCards[i] = Integer.parseInt(tokens.nextToken());
        }
        Arrays.sort(myCards);

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            yourCards[i] = Integer.parseInt(tokens.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(sb);
    }
}