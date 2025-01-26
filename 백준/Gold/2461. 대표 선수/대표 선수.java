import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N; // 학급 수
    private static int M; // 학생 수
    private static int[][] school;

    // 각 학급의 대표 선수를 가리키는 포인터
    private static PriorityQueue<int[]> minHeap;
    private static PriorityQueue<int[]> maxHeap;

    private static int minDiffer;

    private static void solution() {

        while (true) {
            int differ = maxHeap.peek()[2] - minHeap.peek()[2];

            if (minDiffer > differ) {
                minDiffer = differ;
            }

            int[] min = minHeap.poll();
            int minClass = min[0];
            int minIndex = min[1] + 1;

            if (minIndex >= M) return;

            minHeap.add(new int[] { minClass, minIndex, school[minClass][minIndex] });
            maxHeap.add(new int[] { minClass, minIndex, school[minClass][minIndex] });
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        school = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                school[i][j] = Integer.parseInt(tokens.nextToken());
            }
            // 오름차순 정렬
            Arrays.sort(school[i]);
        }

        // 출력하고자 하는 답
        minDiffer = Integer.MAX_VALUE;

        // 현재 대표선수 후보인 N명의 학생들을 능력치 순으로 정렬하기 위함(오름차순)
        minHeap = new PriorityQueue<int[]>(new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        // 현재 대표선수 후보인 N명의 학생들을 능력치 순으로 정렬하기 위함(내림차순)
        maxHeap = new PriorityQueue<int[]>(new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[2], o1[2]);
            }
        });

        // 초기엔 모든 학급이 가장 낮은 능력치를 가지는 학생을 가리킴
        for (int i = 0; i < N; i++) {
            minHeap.add(new int[]{i, 0, school[i][0]});
            maxHeap.add(new int[]{i, 0, school[i][0]});
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(minDiffer);
    }
}