import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static String[] words;
    private static Trie trie;

    private static double solution() {
        int sum = 0;

        for (String word : words) {
            int count = trie.getTypingCount(word);
            sum += count;
        }

        return (double) sum / N;
    }

    private static void init() throws IOException {
        trie = new Trie();
        words = new String[N];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = word;
            trie.insert(word);
        }
    }

    public static void main(String[] args) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            N = Integer.parseInt(line);
            init();
            double answer = solution();
            System.out.printf("%.2f", answer);
            System.out.println();
        }
    }

    static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = this.root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);

                if (i == word.length() - 1) {
                    cur.isEnd = true;
                }
            }
        }

        public int getTypingCount(String word) {
            char start = word.charAt(0);
            TrieNode cur = this.root.children.get(start);
            int count = 1;

            for (int i = 1; i < word.length(); i++) {
                if (cur.children.size() >= 2) {
                    count++;
                } else if (cur.children.size() == 1 && cur.isEnd) {
                    count++;
                }

                char c = word.charAt(i);
                cur = cur.children.get(c);
            }

            return count;
        }
    }

    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd;

        TrieNode() {
            children = new HashMap<>();
            isEnd = false;
        }
    }

}