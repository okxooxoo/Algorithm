import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int r;
    private static int c;
    private static int k;

    private static int[][] matrix;
    private static int rowCount; // 유효한 행 개수
    private static int colCount; // 유효한 열 개수

    private static int getTime() {
        int time = 0;

        while (time <= 100) {
            if (check()) {
                return time;
            }

            calculate();
            time++;
        }

        return -1;
    }

    private static void calculate() {
        // 모든 행에 대하여 R 연산
        if (rowCount >= colCount) {
            for (int row = 0; row < rowCount; row++) {
                RCalculate(row);
            }
        // 모든 열에 대하여 C 연산
        } else {
            for (int col = 0; col < colCount; col++) {
                CCalculate(col);
            }
        }
    }

    // 특정 행에 대하여 R 연산
    private static void RCalculate(int rowNum) {
        // 각 숫자의 등장 횟수 기록
        int[] counting = new int[101];

        for (int i = 0; i < colCount; i++) {
            int number = matrix[rowNum][i];
            if (number > 0) {
                counting[number]++;
            }
        }

        // [수, 등장 횟수]을 최소 힙에 삽입
        // 수의 등장 횟수가 커지는 순으로, 그러한 것이 여러 가지면 수가 커지는 순으로 정렬
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }

            return a[1] - b[1];
        });

        for (int i = 0; i <= 100; i++) {
            if (counting[i] > 0) {
                minHeap.offer(new int[]{i, counting[i]});
            }
        }

        // 정렬
        int index = 0;

        while (!minHeap.isEmpty()) {
            int[] min = minHeap.poll();
            matrix[rowNum][index++] = min[0];
            matrix[rowNum][index++] = min[1];

            if (index >= 100) {
                break;
            }
        }

        // 유효한 열 개수 갱신
        colCount = Math.max(colCount, index);

        // 정렬 후 나머지 0으로 채우기
        for (int i = index; i < 100; i++) {
            matrix[rowNum][i] = 0;
        }
    }

    // 특정 열에 대하여 C 연산
    private static void CCalculate(int colNum) {
        // 각 숫자의 등장 횟수 기록
        int[] counting = new int[101];

        for (int i = 0; i < rowCount; i++) {
            int number = matrix[i][colNum];
            if (number > 0) {
                counting[number]++;
            }
        }

        // [수, 등장 횟수]을 최소 힙에 삽입
        // 수의 등장 횟수가 커지는 순으로, 그러한 것이 여러 가지면 수가 커지는 순으로 정렬
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }

            return a[1] - b[1];
        });

        for (int i = 0; i <= 100; i++) {
            if (counting[i] > 0) {
                minHeap.offer(new int[]{i, counting[i]});
            }
        }

        // 정렬
        int index = 0;

        while (!minHeap.isEmpty()) {
            int[] min = minHeap.poll();
            matrix[index++][colNum] = min[0];
            matrix[index++][colNum] = min[1];

            if (index >= 100) {
                break;
            }
        }

        // 유효한 행 개수 갱신
        rowCount = Math.max(rowCount, index);

        // 정렬 후 나머지 0으로 채우기
        for (int i = index; i < 100; i++) {
            matrix[i][colNum] = 0;
        }
    }

    private static boolean check() {
        return matrix[r][c] == k;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        r = Integer.parseInt(tokens.nextToken()) - 1;
        c = Integer.parseInt(tokens.nextToken()) - 1;
        k = Integer.parseInt(tokens.nextToken());

        matrix = new int[100][100];
        rowCount = 3;
        colCount = 3;

        for (int i = 0; i < 3; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(getTime());
    }
}