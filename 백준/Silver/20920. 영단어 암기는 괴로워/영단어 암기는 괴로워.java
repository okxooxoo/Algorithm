import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer tokens;

    private static int N; // 단어의 개수
    private static int M; // 외울 단어의 길이 기준

    private static HashMap<String, Integer> map;
    private static PriorityQueue<Map.Entry<String, Integer>> pq;

    private static void solution() {
        pq = new PriorityQueue<>((o1, o2) -> {
            if (!Objects.equals(o1.getValue(), o2.getValue())) {
                return o2.getValue().compareTo(o1.getValue());
            }

            if (o1.getKey().length() != o2.getKey().length()) {
                return o2.getKey().length() - o1.getKey().length();
            }

            return o1.getKey().compareTo(o2.getKey());
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.add(entry);
        }
    }

    private static void output() throws IOException {
        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> entry = pq.poll();
            bw.write(entry.getKey() + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (word.length() >= M) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        output();
    }
}
