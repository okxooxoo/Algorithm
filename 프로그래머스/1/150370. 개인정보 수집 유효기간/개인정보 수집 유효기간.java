import java.util.*;

class Solution {
    HashMap<String, Integer> termMap = new HashMap<>();
    ArrayList<Integer> answerList = new ArrayList<>();
    
    // 특정 날짜에 month 더하기
    private static String addMonths(String date, int months) {
        String[] dateStrArr = date.split("\\.");
        int year = Integer.parseInt(dateStrArr[0]);
        int month = Integer.parseInt(dateStrArr[1]);
        int day = Integer.parseInt(dateStrArr[2]);
        
        month += months;
        
        year += (month - 1) / 12;
        month = (month - 1) % 12 + 1;
        
        String result = String.format("%d.%02d.%02d", year, month, day);
        return result;
    }
    
    // 유효 기간 내인지 확인
    private static boolean isValidDate(String today, String date) {
        String[] todayStrArr = today.split("\\.");
        String[] dateStrArr = date.split("\\.");
        int[] todayIntArr = new int[3];
        int[] dateIntArr = new int[3];
        
        for (int i = 0; i < 3; i++) {
            todayIntArr[i] = Integer.parseInt(todayStrArr[i]);
            dateIntArr[i] = Integer.parseInt(dateStrArr[i]);
        }
        
        if (todayIntArr[0] == dateIntArr[0]) {
            if (todayIntArr[1] == dateIntArr[1]) {
                return todayIntArr[2] < dateIntArr[2];
            } else {
                return todayIntArr[1] < dateIntArr[1];
            }
        } else {
            return todayIntArr[0] < dateIntArr[0];
        }
    }
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 약관 정보 저장
        for (String term : terms) {
            String[] termArr = term.split(" ");
            termMap.put(termArr[0], Integer.parseInt(termArr[1]));
        }
        
        for (int i = 0; i < privacies.length; i++) {
            String[] privacyArr = privacies[i].split(" ");
            int month = termMap.get(privacyArr[1]);
            String endDate = addMonths(privacyArr[0], month);
            
            if (!isValidDate(today, endDate)) {
                answerList.add(i + 1);
            }
        }
        
        int[] answer = answerList.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}