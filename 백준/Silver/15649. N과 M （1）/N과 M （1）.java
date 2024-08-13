import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static boolean visited[]; // 방문한 숫자
	private static int choosed[]; // 수열로 선택된 숫자
	
	private static void init() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		visited = new boolean[N];
		choosed = new int[M];
	}
	
	private static void makePermutation(int index) {
		
		if (index >= M) {
			// 수열 출력
			StringBuilder output = new StringBuilder();
			for (int i = 0; i < M; i++) {
				output.append(choosed[i]).append(" ");
			}
			System.out.println(output);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				choosed[index] = i + 1;
				makePermutation(index + 1);
				// 백트래킹
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		makePermutation(0);
	}

}
