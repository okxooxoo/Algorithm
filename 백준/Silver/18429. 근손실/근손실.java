import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int K;
	private static boolean visited[]; // 방문한 숫자
	private static int choosed[]; // 수열로 선택된 숫자
	private static int kits[]; // 운동 키트별 중량 증가량
	private static int count; // 경우의 수

	private static void init() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;

		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());

		visited = new boolean[N];
		choosed = new int[N];
		kits = new int[N];

		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			kits[i] = Integer.parseInt(tokens.nextToken());
		}
	}

	private static void countIfOverWeight() {
		int weight = 500;
		for (int i = 0; i < N; i++) {
			weight += kits[choosed[i]];
			weight -= K;
			if (weight < 500) {
				return;
			}
		}
		count++;
	}

	private static void makePermutation(int index) {
		if (index >= N) {
			countIfOverWeight();
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				choosed[index] = i;
				makePermutation(index + 1);
				// 백트래킹
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		makePermutation(0);
		System.out.println(count);
	}
}
