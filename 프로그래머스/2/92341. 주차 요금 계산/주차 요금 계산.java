import java.util.*;

class Solution {
    private static TreeMap<String, Integer> carMap;
    private static int defaultTime;
    private static int defaultFee;
    private static int unitTime;
    private static int unitFee;
    
    private static int getTime(String s) {
        int hours = Integer.parseInt(s.split(":")[0]);
        int minutes = Integer.parseInt(s.split(":")[1]);
        int time = hours * 60 + minutes;
        return time;
    }
    
    private static int calcFee(int time) {  
        if (time <= defaultTime) return defaultFee;
        int m = (int)(Math.ceil((double)(time - defaultTime) / unitTime));
        int fee = defaultFee + m * unitFee;
        return fee;
    }
    
    public int[] solution(int[] fees, String[] records) {
        carMap = new TreeMap<>();
        defaultTime = fees[0];
        defaultFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];
        
        for (String record : records) {
            String[] recordArr = record.split(" ");
            int time = getTime(recordArr[0]);
            String carNum = recordArr[1];
            
            // 입출차 기록이 없으면 map에 차 번호 삽입
            if (!carMap.containsKey(carNum)) {
                carMap.put(carNum, 0);
            }
            
            if (recordArr[2].equals("IN")) {
                carMap.put(carNum, carMap.get(carNum) - time);
            } else {
                carMap.put(carNum, carMap.get(carNum) + time);
            }
        }
        
        // 출차되지 않은 차 자동으로 23:59에 출차
        for (Map.Entry<String, Integer> entry : carMap.entrySet()) {
            String carNum = entry.getKey();
            int carTime = entry.getValue();
            
            if (carTime <= 0) {
                int time = getTime("23:59");
                carMap.put(carNum, carMap.get(carNum) + time);
            }
            
        }
        
        int len = carMap.keySet().size();
        int[] answer = new int[len];
        int idx = 0;
        
        for (int time : carMap.values()) {
            int ansTime = calcFee(time);
            answer[idx++] = ansTime;
        }
        
        return answer;
    }
}