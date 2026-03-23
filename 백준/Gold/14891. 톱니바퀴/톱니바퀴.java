import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int K; // 회전 횟수
    private static Gear[] gears;

    public static void main(String[] args) throws IOException {
        gears = new Gear[4];

        for (int i = 0; i < 4; i++) {
            Gear gear = new Gear(i);
            gears[i] = gear;
            String[] inputs = br.readLine().split("");
            for (String input : inputs) {
                gear.list.add(Integer.parseInt(input));
            }
        }

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            tokens = new StringTokenizer(br.readLine());
            // 방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향
            int num = Integer.parseInt(tokens.nextToken()) - 1; // 0-indexed
            int rotDirection = Integer.parseInt(tokens.nextToken());
            simulation(num, rotDirection, "");
        }

        int answer = 0;
        for (Gear gear : gears) {
            answer += gear.calcScore();
        }

        System.out.println(answer);
    }

    private static void simulation(int num, int rotDirection, String direction) {
        int leftStatus = gears[num].getLeft();
        int rightStatus = gears[num].getRight();

        if (rotDirection == -1) {
            gears[num].rotateLeft();
        } else {
            gears[num].rotateRight();
        }

        int leftIdx = num - 1;
        int rightIdx = num + 1;

        // 왼쪽으로 영향
        if (leftIdx >= 0 && !direction.equals("Right")) {
            if (leftStatus != gears[leftIdx].getRight()) {
                simulation(leftIdx, -rotDirection, "Left");
            }
        }
        // 오른쪽으로 영향
        if (rightIdx < 4 && !direction.equals("Left")) {
            if (rightStatus != gears[rightIdx].getLeft()) {
                simulation(rightIdx, -rotDirection, "Right");
            }
        }
    }

    static class Gear {
        int num;
        ArrayList<Integer> list;
        int left;
        int right;
        int top;

        public Gear(int num) {
            this.num = num;
            this.list = new ArrayList<>();
            this.left = 6;
            this.right = 2;
            this.top = 0;
        }

        void rotateLeft() {
            left = (left + 1) % 8;
            right = (right + 1) % 8;
            top = (top + 1) % 8;
        }

        void rotateRight() {
            left = (left + 7) % 8;
            right = (right + 7) % 8;
            top = (top + 7) % 8;
        }

        int getLeft() {
            return list.get(left);
        }
        int getRight() {
            return list.get(right);
        }

        int calcScore() {
            return list.get(top) == 1 ? (int)Math.pow(2, num) : 0;
        }
    }
}