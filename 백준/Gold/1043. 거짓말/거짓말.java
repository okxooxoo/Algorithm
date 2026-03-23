import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 사람 수
    private static int M; // 파티 수

    private static Party[] parties;
    private static int[] parents;

    private static void initSet() {
        parents = new int[N + 1];
        Arrays.fill(parents, -1);
    }

    private static int getSet(int a) {
        if (parents[a] < 0) {
           return a;
        }

        return getSet(parents[a]);
    }

    private static boolean isSameSet(int a, int b) {
        return getSet(a) == getSet(b);
    }

    private static boolean union(int a, int b) {
        if (isSameSet(a, b)) {
            return false;
        }

        int aParent = getSet(a);
        int bParent = getSet(b);

        // 더 작은 수에 합치기
        if (aParent < bParent) {
            parents[aParent] += parents[bParent];
            parents[bParent] = aParent;
        } else {
            parents[bParent] += parents[aParent];
            parents[aParent] = bParent;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        parties = new Party[M + 1];

        initSet();

        tokens = new StringTokenizer(br.readLine());
        int knowCnt = Integer.parseInt(tokens.nextToken());

        for (int i = 1; i <= knowCnt; i++) {
            int person = Integer.parseInt(tokens.nextToken());
            union(person, 0);
        }

        for (int i = 1; i <= M; i++) {
            Party party = new Party(i);
            parties[i] = party;

            tokens = new StringTokenizer(br.readLine());
            int participantCnt = Integer.parseInt(tokens.nextToken());
            int[] participants = new int[participantCnt + 1];
            for (int j = 1; j <= participantCnt; j++) {
                participants[j] = Integer.parseInt(tokens.nextToken());
                party.set.add(participants[j]);

                if (j > 1) {
                    union(participants[j], participants[j - 1]);
                }
            }
        }

        for (int i = 1; i <= M; i++) {
            Party party = parties[i];
            for (int person : party.set) {
                if (getSet(person) == 0) {
                    party.isTruth = true;
                    break;
                }
            }
        }

        int answer = M;
        for (int i = 1; i <= M; i++) {
            Party party = parties[i];
            if (party.isTruth) answer--;
        }

        System.out.println(answer);
    }

    static class Party {
        int num;
        HashSet<Integer> set;
        boolean isTruth;

        public Party(int num) {
            this.num = num;
            this.set = new HashSet<>();
            this.isTruth = false;
        }
    }
}