import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;

	private static int N;
	private static int M;
	private static ArrayList<ArrayList<Integer>> graph; // 인접 리스트
	private static boolean[] visited;
	private static boolean flag; // ABCDE 관계가 존재하는지 여부

	private static void dfs(int node, int depth) {
		if (depth == 4) {
			flag = true;
			return;
		}

		for (int nextNode : graph.get(node)) {
			if (!visited[nextNode]) {
				visited[nextNode] = true;
				dfs(nextNode, depth + 1);
				if (flag)
					return;
				// 백트래킹
				visited[nextNode] = false;
			}
		}
	}

	private static void solution() {
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			dfs(i, 0);
			if (flag)
				break;
			// 백트래킹
			visited[i] = false;
		}
	}

	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		graph = new ArrayList<>();
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());

			graph.get(a).add(b);
			graph.get(b).add(a);
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		solution();
		if (flag) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

}