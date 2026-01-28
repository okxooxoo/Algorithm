import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb;

    private static int N;
    private static int K; // 등수를 알고 싶은 국가
    private static Nation[] nations;

    private static int solve() {
        Arrays.sort(nations);

        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (i == 0) {
                answer = 1;
            } else if (nations[i].compareTo(nations[i - 1]) != 0) {
                answer = i + 1;
            }

            if (nations[i].num == K) {
                return answer;
            }
        }
        return -1;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        nations = new Nation[N];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(tokens.nextToken());
            int gold = Integer.parseInt(tokens.nextToken());
            int silver = Integer.parseInt(tokens.nextToken());
            int bronze = Integer.parseInt(tokens.nextToken());
            nations[num - 1] = new Nation(num, gold, silver, bronze);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    static class Nation implements Comparable<Nation> {
        int num;
        int gold;
        int silver;
        int bronze;

        Nation(int num, int gold, int silver, int bronze) {
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Nation o) {
            if (this.gold != o.gold) {
                return Integer.compare(o.gold, this.gold);
            } else if (this.silver != o.silver) {
                return Integer.compare(o.silver, this.silver);
            } else {
                return Integer.compare(o.bronze, this.bronze);
            }
        }
    }
}