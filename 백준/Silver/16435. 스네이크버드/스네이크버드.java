import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;

	private static int N;
	private static int L;
	private static int[] fruits;

	private static void solution() {
		for (int fLength : fruits) {
			if (fLength <= L) {
				L++;
			} else {
				break;
			}
		}
	}

	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		L = Integer.parseInt(tokens.nextToken());

		tokens = new StringTokenizer(br.readLine());
		fruits = new int[N];
		for (int i = 0; i < N; i++) {
			fruits[i] = Integer.parseInt(tokens.nextToken());
		}

		Arrays.sort(fruits); // 오름차순으로 정렬
	}

	public static void main(String[] args) throws IOException {
		init();
		solution();
		System.out.println(L);
	}

}