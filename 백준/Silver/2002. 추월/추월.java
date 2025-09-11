import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N; // 차의 대수
    private static HashMap<String, Integer> departure;
    private static String[] arrival;

    private static int answer;

    private static void solution() {
        int min = departure.get(arrival[N - 1]); // 터널에서 가장 마지막으로 나온 자동차의 터널에 들어간 순서

        for (int i = N - 2; i >= 0; i--) {
            if (min < departure.get(arrival[i])) {
                answer++;
            } else {
                min = departure.get(arrival[i]);
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        answer = 0;

        departure = new HashMap<>();
        arrival = new String[N];

        for (int i = 0; i < N; i++) {
            String carNumber = br.readLine();
            departure.put(carNumber, i);
        }

        for (int i = 0; i < N; i++) {
            String carNumber = br.readLine();
            arrival[i] = carNumber;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(answer);
    }
}
