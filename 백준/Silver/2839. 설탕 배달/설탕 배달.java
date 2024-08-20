import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());

		int temp = N % 5;
		int fiveNum = N / 5;
		int threeNum = 0;

		while (temp <= N) {
			if (temp % 3 == 0) {
				threeNum = temp / 3;
				break;
			}
			temp += 5;
			fiveNum -= 1;
		}

		if (temp > N) {
			System.out.println(-1);
		} else {
			System.out.println(fiveNum + threeNum);
		}
	}

}