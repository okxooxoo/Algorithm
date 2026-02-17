import java.util.*;

class Solution {
    private static String[] words;
    private static HashMap<String, Boolean> visited;
    
    private static int BFS(String begin, String target) {
        ArrayDeque<Word> dq = new ArrayDeque<>();
        dq.offer(new Word(begin, 0));
        
        while (!dq.isEmpty()) {
            Word cur = dq.pollFirst();
            String curWord = cur.word;
            int curCnt = cur.cnt;
            
            if (curWord.equals(target)) {
                return curCnt;
            }
            
            // 방문 처리
            visited.put(curWord, true);
            
            for (String nxtWord : words) {
                if (visited.get(nxtWord)) continue;
                // 한 개의 알파벳 차이만 존재하면
                if (cur.isOneDiff(nxtWord)) {
                    dq.addLast(new Word(nxtWord, curCnt + 1));
                }
            }
        }
        
        return 0;
    }
    
    public int solution(String begin, String target, String[] words) {
        this.words = words;
        this.visited = new HashMap<>();
        
        for (String word : words) {
            visited.put(word, false);
        }
        
        return BFS(begin, target);
    }
    
    static class Word {
        String word;
        int cnt;
        
        Word(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
        
        // 한 개의 알파벳 차이만 존재하는지 판단
        boolean isOneDiff(String other) {
            char[] A = word.toCharArray();
            char[] B = other.toCharArray();
            
            int diff = 0;
            for (int i = 0; i < A.length; i++) {
                if (A[i] != B[i]) {
                    diff++;
                }
            }
            
            return diff == 1;
        }
    }
}