import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;

	private static int R;
	private static int C;
	private static boolean[][] visited;
	private static int count;
	private static boolean flag;
	private static final int[][] moves = { { -1, 1 }, { 0, 1 }, { 1, 1 } };

	private static void dfs(int r, int c) {
		// 근처 빵집에 도착하면
		if (c == C - 1) {
			count++;
			flag = true;
			return;
		}

		for (int[] move : moves) {
			int nr = r + move[0];
			int nc = c + move[1];
			if (nr >= 0 && nr < R && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc);
				if (flag) {
					return;
				}
			}
		}
	}

	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());

		visited = new boolean[R][C];
		count = 0;

		for (int r = 0; r < R; r++) {
			String[] tokens = br.readLine().split("");
			for (int c = 0; c < C; c++) {
				if (tokens[c].equals("x")) {
					visited[r][c] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		for (int r = 0; r < R; r++) {
			flag = false;
			dfs(r, 0);
		}
		System.out.println(count);
	}

}