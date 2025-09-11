import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N; // 차의 대수

    private static String[] departure;
    private static HashMap<String, Integer> arrival;

    private static int answer;

    private static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arrival.get(departure[i]) < arrival.get(departure[j])) {
                    answer++;
                    break;
                }
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        answer = 0;

        departure = new String[N];
        arrival = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String carNumber = br.readLine();
            departure[i] = carNumber;
        }

        for (int i = 0; i < N; i++) {
            String carNumber = br.readLine();
            arrival.put(carNumber, i);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(answer);
    }
}
