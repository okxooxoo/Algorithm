import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;

	private static int N;
	private static int[] solutions; // 모든 용액의 특성값
    
    private static int left;
	private static int right;
	private static int answerLeft;
	private static int answerRight;
    private static int closestToZeroValue; // 0에 가까운 특성값

	// 투 포인터
	private static void makeCombination() {
		left = 0;
		right = N - 1;

		while (left < right) {
			int sum = solutions[left] + solutions[right];
			if (closestToZeroValue > Math.abs(sum)) {
				closestToZeroValue = Math.abs(sum);
				answerLeft = left;
				answerRight = right;
			}

			if (sum > 0) {
				right--;
			} else if (sum < 0) {
				left++;
			} else {
				answerLeft = left;
				answerRight = right;
				return;
			}
		}
	}

	// 입력
	private static void init() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		solutions = new int[N];
		closestToZeroValue = 2000000000;

		tokens = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			solutions[i] = Integer.parseInt(tokens.nextToken());
		}

		Arrays.sort(solutions);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		makeCombination();
		System.out.println(solutions[answerLeft] + " " + solutions[answerRight]);
	}

}