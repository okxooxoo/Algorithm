import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int[][] deltas = {{0,0}, {1,0}, {0,1}, {-1,0}, {0,-1}};

    private static int N;
    private static int[][] ground;
    private static Flower[] flowers;
    private static int answer;

    private static void DFS(int depth) {
        if (depth == 3) {
            if (flowers[0].isDuplicated(flowers[1]) || flowers[1].isDuplicated(flowers[2]) || flowers[2].isDuplicated(flowers[0])) {
                return;
            }

            int amount = flowers[0].getAmount() + flowers[1].getAmount() + flowers[2].getAmount();
            answer = Math.min(answer, amount);
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                flowers[depth] = new Flower(i, j);
                DFS(depth + 1);
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        ground = new int[N][N];
        flowers = new Flower[3];
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ground[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        DFS(0);
        System.out.println(answer);
    }

    static class Flower {
        int r;
        int c;
        int[][] coords;

        Flower(int r, int c) {
            this.r = r;
            this.c = c;
            this.coords = new int[5][2];
            setCoords(); // 꽃이 차지하는 좌표를 세팅
        }

        void setCoords() {
            for (int i = 0; i < 5; i++) {
                int nr = this.r + deltas[i][0];
                int nc = this.c + deltas[i][1];
                this.coords[i][0] = nr;
                this.coords[i][1] = nc;
            }
        }

        int getAmount() {
            int sum = 0;

            for (int i = 0; i < 5; i++) {
                int r = coords[i][0];
                int c = coords[i][1];

                sum += ground[r][c];
            }

            return sum;
        }

        // 하나라도 겹치는 좌표가 있으면 false 반환
        boolean isDuplicated(Flower other) {
            for (int[] myCoord : this.coords) {
                int myR = myCoord[0];
                int myC = myCoord[1];
                
                for (int[] otherCoord : other.coords) {
                    int otherR = otherCoord[0];
                    int otherC = otherCoord[1];
                    
                    if (myR == otherR && myC == otherC) {
                        return true;
                    }
                }
            }
            
            return false;
        }
    }
}