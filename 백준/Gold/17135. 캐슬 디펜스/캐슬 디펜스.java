import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;

	private static int N;
	private static int M;
	private static int D; // 공격 거리 제한
	private static int[][] map; // 적의 배치 정보
	private static int[][] curMap; // 현재 적의 배치 정보
	private static int[] choosed; // 선택한 궁수의 column 인덱스
	private static int[][] killed; // 궁수가 공격한 적의 row, column 좌표
	private static int monterCount; // 총 살아있는 적 개수
	private static int curMonterCount; // 현재 살아있는 적 개수
	private static int maxKillCount; // 출력하고자 하는 답

	private static void moveMonster() {
		for (int j = 0; j < M; j++) {
			// 가장 앞에 있는 적 제외
			if (curMap[N - 1][j] == 1) {
				curMonterCount--;
			}
		}

		// 적 위치 앞으로 한 칸씩 이동
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				curMap[i + 1][j] = curMap[i][j];
			}
		}
		
		// 마지막 줄은 비워야 함
		for (int j = 0; j < M; j++) {
			curMap[0][j] = 0;
		}
	}

	private static void kill(int index, int archerIndex) {
		for (int k = 1; k <= D; k++) {
			for (int j = 0; j < M; j++) {
				for (int i = N - 1; i >= 0; i--) {
					if (curMap[i][j] == 1 && Math.abs(N - i) + Math.abs(archerIndex - j) == k) {
						killed[index] = new int[] { i, j };
						return;
					}
				}
			}
		}

		killed[index] = new int[] { N, 0 };
	}

	private static void gameStart() {
		int killCount = 0;
		curMonterCount = monterCount;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				curMap[i][j] = map[i][j];
			}
		}

		while (true) {
			// 다 죽였으면 루프 종료
			if (curMonterCount <= 0) {
				maxKillCount = Math.max(maxKillCount, killCount);
				return;
			}

			// 모든 궁수 공격
			for (int i = 0; i < 3; i++) {
				kill(i, choosed[i]);
			}

			// 제거한 적의 수 카운트
			for (int[] coord : killed) {
				int r = coord[0];
				int c = coord[1];
				if (curMap[r][c] == 1) {
					curMap[r][c] = 0;
					curMonterCount--;
					killCount++;
				}
			}

			// 적 앞으로 전진
			moveMonster();
		}
	}

	// 궁수의 위치 3개를 선택하는 조합
	private static void makeCombination() {
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				for (int k = j + 1; k < M; k++) {
					choosed[0] = i;
					choosed[1] = j;
					choosed[2] = k;
					// 게임 시작
					gameStart();
				}
			}
		}
	}

	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		D = Integer.parseInt(tokens.nextToken());

		map = new int[N + 1][M];
		curMap = new int[N + 1][M];
		choosed = new int[3];
		killed = new int[3][2];
		monterCount = 0;
		curMonterCount = 0;
		maxKillCount = 0;

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if (map[i][j] == 1) {
					monterCount++;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		makeCombination();
		System.out.println(maxKillCount);
	}

}