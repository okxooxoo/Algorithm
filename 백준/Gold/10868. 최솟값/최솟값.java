import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] tree;
	static int[] numbers;
	
	// 세그먼트 트리 초기화 
	public static int init(int start, int end, int node) {
		// 리프 노드에 도달하면
		if (start == end) {
			tree[node] = numbers[start];
			return tree[node];
		}
		int mid = (start + end) / 2;
		tree[node] = Math.min(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
		return tree[node];
	}
	
	// 구간 최솟값 구하기 
	public static int getSectionMin(int start, int end, int node, int left, int right) {
		// 범위 밖에 있는 경우
		if (left > end || right < start) {
			return Integer.MAX_VALUE;
		}
		// 범위 안에 있는 경우 
		if (left <= start && right >= end) {
			return tree[node];
		}
		// 둘 다 아닌 경우 (여러 범위에 걸쳐 있는 경우) 
		int mid = (start + end) / 2;
		return Math.min(getSectionMin(start, mid, node * 2, left, right), getSectionMin(mid + 1, end, node * 2 + 1, left, right));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		numbers = new int[N];
		for (int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		// 트리의 높이
		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		// 트리 내 노드 개수 
		int nodeNum = (int) Math.pow(2, h+1);
		tree = new int[nodeNum];
		
		init(0, N-1, 1);

		// 구간 내 최솟값 출력 
		for (int i=0; i<M; i++) {
			String[] section = br.readLine().split(" ");
			int a = Integer.parseInt(section[0]);
			int b = Integer.parseInt(section[1]);
			System.out.println(getSectionMin(0, N-1, 1, a-1, b-1));
		}
	}

}
