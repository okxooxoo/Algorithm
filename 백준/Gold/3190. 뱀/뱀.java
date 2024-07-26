import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	// 뱀의 위치 정보
	private static LinkedList<int[]> snake = new LinkedList<>();
	
	// 자신의 몸과 부딪히는지 여부 반환
    public static boolean isBumpedSelf(int headR, int headC) {
        for (int i = snake.size() - 2; i >= 0; i--) {
        	if (headR == snake.get(i)[0] && headC == snake.get(i)[1]) {
        		return true;
        	}
        }
        return false;
    }

	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    int N = Integer.parseInt(br.readLine()); // 보드의 크기
	    int K = Integer.parseInt(br.readLine()); // 사과의 개수
	    
	    boolean[][] board = new boolean[N][N];
	    
	    for (int k=0; k<K; k++) {
	        st = new StringTokenizer(br.readLine());
	        int r = Integer.parseInt(st.nextToken());
	        int c = Integer.parseInt(st.nextToken());
	        board[r-1][c-1] = true;
	    }
	    
        // 뱀의 방향 변환 횟수
	    int L = Integer.parseInt(br.readLine());
	    
	    // 뱀의 방향 변환 정보 
	    HashMap<Integer, String> directionInfo = new HashMap<Integer, String>();
	    for (int l=0; l<L; l++) {
	        st = new StringTokenizer(br.readLine());
	        int time = Integer.parseInt(st.nextToken());
	        String direction = st.nextToken();
	        directionInfo.put(time, direction);
	    }
	    
	    // 우 하 좌 상
	    int[][] moves = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
	    int movesKeyIndex = 0;
	    
	    // 뱀의 머리 추가 
	    snake.add(new int[] {0, 0});
	    
	    int time = 0;
	    
	    // 게임 시작
	    while (true) {
	        time++;
	        // 몸 길이 늘리기
	        int[] head = snake.getLast();
	        int headR = head[0] + moves[movesKeyIndex][0];
	        int headC = head[1] + moves[movesKeyIndex][1];
	        snake.add(new int[] {headR, headC});

	        // 보드 범위를 벗어나면
	        if (headR < 0 || headC < 0 || headR >= N || headC >= N) {
	            break;
	        }
	        
	        // 뱀이 자신의 몸과 부딪히면
	        if (isBumpedSelf(headR, headC)) {
	            break;
	        }
	        
	        // 이동한 칸에 사과가 있다면
	        if (board[headR][headC]) {
	            board[headR][headC] = false;
	        } else {
	        	// 이동한 칸에 사과가 없다면 
	        	snake.removeFirst();
	        }
	        
	        // 방향 전환 
	        if (directionInfo.containsKey(time)) {
	        	if (directionInfo.get(time).equals("L")) {
	        		movesKeyIndex = (movesKeyIndex + 3) % 4;
	        	} else {
	        		movesKeyIndex = (movesKeyIndex + 1) % 4;
	        	}
	        }
	        
	    }
	    
	    System.out.println(time);
	}
}