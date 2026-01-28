import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        int[] numbers = new int[10];
        String[] inputs = br.readLine().split("");

        for (String input : inputs) {
            numbers[input.charAt(0) - '0']++;
        }

        int mx = 0;
        for (int i = 0; i < 10; i++) {
            if (i == 6 || i == 9) continue;
            mx = Math.max(mx, numbers[i]);
        }

        mx = Math.max(mx, (numbers[6] + numbers[9] + 1) / 2);

        System.out.println(mx);
    }
}