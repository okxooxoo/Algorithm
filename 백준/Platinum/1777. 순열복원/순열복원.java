import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] tree;
	
	// 세그먼트 트리 초기화 
	public static int init(int start, int end, int node) {
		// 리프 노드에 도달하면
		if (start == end) {
			tree[node] = 1;
			return tree[node];
		}
		int mid = (start + end) / 2;
		tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
		return tree[node];
	}
	
	// 원래 순열에 위치해야 할 index을 찾는다! 
	public static int getIndex(int start, int end, int node, int value) {
		// 리프 노드에 도달하면 
		if (start == end) {
			return start;
		}
		int mid = (start + end) / 2;
		// 오른쪽 자식 노드에 빈 자리가 충분하면 
		if (value < tree[node * 2 + 1]) {
			return getIndex(mid + 1, end, node * 2 + 1, value);
		} else {
			return getIndex(start, mid, node * 2, value - tree[node * 2 + 1]);
		}
	}
	
	// 세그먼트 트리 갱신 
	public static void update(int start, int end, int node, int index) {
		// 구간 안에 index가 없으면 갱신할 필요 없음 
		if (start > index || end < index) {
			return;
		}
		tree[node]--;
		// 리프 노드에 도달하면 
		if (start == end) {
			return;
		}
		int mid = (start + end) / 2;
		update(start, mid, node * 2, index);
	    update(mid + 1, end, node * 2 + 1, index);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		// 트리의 높이
		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		// 트리 내 노드 개수 
		int nodeNum = (int) Math.pow(2, h+1);
		tree = new int[nodeNum];
		
		// 반전 수열
		int [] inversionSequence = new int[N];
		// 원본 수열
		int [] originalSequence = new int[N];
		
		String [] nums = br.readLine().split(" ");
		for (int i=0; i<N; i++) {
			inversionSequence[i] = Integer.parseInt(nums[i]);
		}
		
		init(0, N-1, 1);
		// 원본 수열 구하기 
		for (int i=N-1; i>=0; i--) {
			int index = getIndex(0, N-1, 1, inversionSequence[i]);
			originalSequence[index] = i+1;
			update(0, N-1, 1, index);
		}
		
		// 원본 수열 출력 
		for (int num: originalSequence) {
			System.out.print(num + " ");
		}
	}

}
