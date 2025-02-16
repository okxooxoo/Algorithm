import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());

            int dest = (1 << 10) - 1;
            int visited = 0;

            for (int i = 1; i <= 100; i++) {
                int M = N * i;

                for (String s: Integer.toString(M).split("")) {
                    int num = s.charAt(0) - '0';
                    visited |= (1 << num);
                }

                if (dest == visited) {
                    sb.append("#").append(t).append(" ").append(M).append("\n");
                    break;
                }
            }
        }

        System.out.println(sb);
    }
}