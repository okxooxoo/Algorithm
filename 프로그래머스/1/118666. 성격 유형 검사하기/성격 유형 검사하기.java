import java.util.*;

class Solution {
    private static char[][] types = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
    private static int[] scores = {0, 3, 2, 1, 0, 1, 2, 3};
    
    private static HashMap<Character, Integer> scoreMap = new HashMap<>();
    
    public String solution(String[] survey, int[] choices) {
        // 점수판 초기화
        for (char[] type: types) {
            scoreMap.put(type[0], 0);
            scoreMap.put(type[1], 0);
        }
        
        for (int i = 0; i < choices.length; i++) {
            // 동의할 경우
            if (choices[i] > 4) {
                char choice = survey[i].charAt(1);
                scoreMap.put(choice, scoreMap.get(choice) + scores[choices[i]]);
            // 비동의할 경우
            } else if (choices[i] < 4){
                char choice = survey[i].charAt(0);
                scoreMap.put(choice, scoreMap.get(choice) + scores[choices[i]]);
            }
        }
        
        // 검사 결과 구하기
        String answer = "";
        
        for (char[] type: types) {
            answer += scoreMap.get(type[0]) >= scoreMap.get(type[1]) ? type[0] : type[1];
        }
        
        return answer;
    }
}