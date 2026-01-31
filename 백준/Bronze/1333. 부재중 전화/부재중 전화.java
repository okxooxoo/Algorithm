import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokens.nextToken()); // 곡의 개수
        int L = Integer.parseInt(tokens.nextToken()); // 곡의 길이
        int D = Integer.parseInt(tokens.nextToken()); // 전화벨 간격
        int answer = 0;

        // 곡이 끝나는 시간
        int endTime = (L + 5) * N - 5;
        // 현재 시간
        int time = 0;

        while (true) {
            if (time >= endTime) {
                // 전화벨 울리는지 확인
                if (time % D == 0) {
                    answer = time;
                    break;
                }
            } else {
                // 곡 재생되고 있지 않은지 확인
                if (time % (L + 5) >= L) {
                    // 전화벨 울리는지 확인
                    if (time % D == 0) {
                        answer = time;
                        break;
                    }
                }
            }

            time++;
        }

        System.out.println(answer);
    }
}