import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int M;

    private static ArrayList<int[]> house = new ArrayList<>();
    private static ArrayList<int[]> chicken = new ArrayList<>();
    private static boolean[][] openChicken;
    private static int minChickenDistance = Integer.MAX_VALUE;

    private static int calcChickenDistance(int[] start) {
        int distance = Integer.MAX_VALUE;

        for (int[] coord : chicken) {
            int r = coord[0];
            int c = coord[1];
            if (openChicken[r][c]) {
                int temp = Math.abs(start[0] - r) + Math.abs(start[1] - c);
                distance = Math.min(distance, temp);
            }
        }

        return distance;
    }

    private static void makeCombination(int depth, int start) {
        if (depth == M) {
            int distance = 0;
            for (int[] h : house) {
                distance += calcChickenDistance(h);
            }
            minChickenDistance = Math.min(minChickenDistance, distance);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            int r = chicken.get(i)[0];
            int c = chicken.get(i)[1];
            openChicken[r][c] = true;
            makeCombination(depth + 1, i + 1);
            openChicken[r][c] = false;
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        openChicken = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(tokens.nextToken());
                if (temp == 1) {
                    house.add(new int[]{i, j});
                }
                if (temp == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        makeCombination(0, 0);
        System.out.println(minChickenDistance);
    }
}