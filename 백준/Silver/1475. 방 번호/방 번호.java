import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        char[] N = br.readLine().toCharArray();
        int[] count = new int[10];

        for (char c : N) {
            count[c - '0']++;
        }

        int answer = 0;

        for (int i = 0; i < 10; i++) {
            if (answer < count[i]) {
                if (i == 6 || i == 9) {
                    answer = Math.max(answer, ((count[6] + count[9]) + 1) / 2);
                } else {
                    answer = count[i];
                }
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}
