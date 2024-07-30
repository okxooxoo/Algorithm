import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		
		int N = Integer.parseInt(input.readLine());
		int M = Integer.parseInt(input.readLine());
		
		int[][] ground = new int[N][N];
		int curRow = N / 2;
		int curCol = N / 2;
		int ansRow = 0;
		int ansCol = 0;
		int count = 1;
		
		// 상 우 하 좌 
		int[][] moves = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
		int direction = 3;
		ground[curRow][curCol] = count++;
		
		while (count <= N*N) {
			if (count - 1 == M) {
				ansRow = curRow;
				ansCol = curCol;
			}
			int newDirection = (direction + 1) % 4;
			int newRow = curRow + moves[newDirection][0];
			int newCol = curCol + moves[newDirection][1];
			if (ground[newRow][newCol] == 0) {
				direction = newDirection;
				curRow = newRow;
				curCol = newCol;
				ground[curRow][curCol] = count++;
			} else {
				curRow = curRow + moves[direction][0];
				curCol = curCol + moves[direction][1];
				ground[curRow][curCol] = count++;
			}			
		}
		
		// 출력
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				output.append(ground[i][j]).append(" ");
			}
			output.append("\n");
		}
		
		output.append(ansRow+1).append(" ").append(ansCol+1);
		System.out.println(output.toString());
	}
}