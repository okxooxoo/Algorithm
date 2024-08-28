import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static class Camera {
		// 카메라 좌표
		int r;
		int c;
		// 카메라 번호
		int number;

		// 감시할 수 있는 방향
		ArrayList<Integer> directions = new ArrayList<>();

		Camera(int r, int c, int number) {
			this.r = r;
			this.c = c;
			this.number = number;
			initCamera();
		}

		// 감시할 수 있는 방향 초기화
		void initCamera() {
			switch (this.number) {
			case 1:
				directions.add(0);
				break;
			case 2:
				directions.add(0);
				directions.add(2);
				break;
			case 3:
				directions.add(0);
				directions.add(1);
				break;
			case 4:
				directions.add(0);
				directions.add(1);
				directions.add(2);
				break;
			case 5:
				directions.add(0);
				directions.add(1);
				directions.add(2);
				directions.add(3);
				break;
			}
		}

		void changeAngle() {
			for (int i = 0; i < this.directions.size(); i++) {
				int nd = (this.directions.get(i) + 1) % 4;
				this.directions.set(i, nd);
			}
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;

	private static int N;
	private static int M;
	private static int[][] map;
	private static int[][] curMap;
	private static int minCount;
	private static ArrayList<Camera> cameras = new ArrayList<>();

	// 상, 하, 좌, 우
	private static final int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static void simulation(int index) {
		// 모든 카메라에 대하여 각도를 결정했으면
		if (index == cameras.size()) {
			initMap();
			CameraOn();
			int count = countBlindSpot();
			minCount = Math.min(minCount, count);
			return;
		}

		// 4방향으로 카메라 각도 조절
		for (int i = 0; i < 4; i++) {
			simulation(index + 1);
			cameras.get(index).changeAngle();
		}
	}

	// CCTV 작동 시작
	private static void CameraOn() {
		for (Camera camera : cameras) {
			for (int direction : camera.directions) {
				int r = camera.r + deltas[direction][0];
				int c = camera.c + deltas[direction][1];

				while (true) {
					// 범위를 벗어나면
					if (r < 0 || c < 0 || r >= N || c >= M) {
						break;
					}
					// 벽이면
					if (curMap[r][c] == 6) {
						break;
					}
					// 감시할 수 있으면 -1로 표시
					if (curMap[r][c] == 0) {
						curMap[r][c] = -1;
					}

					// 다음 좌표로 이동
					r += deltas[direction][0];
					c += deltas[direction][1];
				}
			}
		}
	}

	// 사각 지대의 개수 카운트
	private static int countBlindSpot() {
		int count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (curMap[i][j] == 0) {
					count++;
				}
			}
		}

		return count;
	}

	// 카메라 객체를 생성하여 리스트에 추가
	private static void addCamera(int r, int c, int cameraNumber) {
		switch (cameraNumber) {
		case 1:
			cameras.add(new Camera(r, c, cameraNumber));
			break;
		case 2:
			cameras.add(new Camera(r, c, cameraNumber));
			break;
		case 3:
			cameras.add(new Camera(r, c, cameraNumber));
			break;
		case 4:
			cameras.add(new Camera(r, c, cameraNumber));
			break;
		case 5:
			cameras.add(new Camera(r, c, cameraNumber));
			break;
		}
	}

	// 현재 지도맵 초기화
	private static void initMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				curMap[i][j] = map[i][j];
			}
		}
	}

	// 입력
	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		map = new int[N][M];
		curMap = new int[N][M];
		minCount = N * M;

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());

				if (map[i][j] >= 1 && map[i][j] <= 5) {
					addCamera(i, j, map[i][j]);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		simulation(0);
		System.out.println(minCount);
	}

}