import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;

	private static int N;
	private static final int[][] deltas = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static char[][] corridor;
	private static ArrayList<int[]> T = new ArrayList<int[]>(); // 선생님 위치
	private static ArrayList<int[]> X = new ArrayList<int[]>(); // 장애물 위치
	private static boolean flag; // 장애물 3개로 감시 피할 수 있는지 여부

	private static void makeCombination(int depth, int start) {
		if (depth == 3) {
			if (!findStudent()) {
				flag = true;
			}
			return;
		}

		for (int i = start; i < X.size(); i++) {
			int r = X.get(i)[0];
			int c = X.get(i)[1];
			corridor[r][c] = 'O';
			makeCombination(depth + 1, i + 1);
			corridor[r][c] = 'X'; // 백트래킹
			
			if (flag) {
				return;
			}
		}
	}

	private static boolean findStudent() {
		for (int[] teacher : T) {

			for (int[] delta : deltas) {
				int currentR = teacher[0];
				int currentC = teacher[1];

				while (currentR >= 0 && currentC >= 0 && currentR < N && currentC < N) {
					// 학생을 만나면 장애물 세우기
					if (corridor[currentR][currentC] == 'S') {
						return true;
						// 장애물을 만나면 탐색 중지
					} else if (corridor[currentR][currentC] == 'O') {
						break;
					}
					currentR += delta[0];
					currentC += delta[1];
				}
			}
		}
		return false;
	}

	private static void init() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		corridor = new char[N][N];
		flag = false;

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				char temp = tokens.nextToken().charAt(0);
				corridor[i][j] = temp;
				// 선생님 위치 저장
				if (temp == 'T') {
					T.add(new int[] { i, j });
				}
				// 빈 위치 저장
				if (temp == 'X') {
					X.add(new int[] { i, j });
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		makeCombination(0, 0);

		if (flag) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

}
