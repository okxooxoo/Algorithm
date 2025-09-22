import java.util.*;

class Solution {
    HashMap<Character, Integer> scoreMap;
    
    public String solution(String[] survey, int[] choices) {
        scoreMap = new HashMap<>();
        
        for (int i = 0; i < choices.length; i++) {
            int score = choices[i] - 4;
            // 동의할 경우
            if (score > 0) {
                char choice = survey[i].charAt(1);
                scoreMap.put(choice, scoreMap.getOrDefault(choice, 0) + score);
            // 비동의할 경우
            } else if (score < 0){
                char choice = survey[i].charAt(0);
                scoreMap.put(choice, scoreMap.getOrDefault(choice, 0) + Math.abs(score));
            }
        }
        
        // 성격 유형 검사 결과 구하기
        String answer = "";
        
        if (scoreMap.getOrDefault('R', 0) >= scoreMap.getOrDefault('T', 0)) {
            answer += "R";
        } else {
            answer += "T";
        }
        
        if (scoreMap.getOrDefault('C', 0) >= scoreMap.getOrDefault('F', 0)) {
            answer += "C";
        } else {
            answer += "F";
        }
        
        if (scoreMap.getOrDefault('J', 0) >= scoreMap.getOrDefault('M', 0)) {
            answer += "J";
        } else {
            answer += "M";
        }
        
        if (scoreMap.getOrDefault('A', 0) >= scoreMap.getOrDefault('N', 0)) {
            answer += "A";
        } else {
            answer += "N";
        }
        
        return answer;
    }
}