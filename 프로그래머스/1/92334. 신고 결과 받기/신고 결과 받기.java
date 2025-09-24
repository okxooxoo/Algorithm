import java.util.*;

class Solution {
    private static int threshold; // 정지 기준
    private static HashMap<String, User> users; // 모든 유저
    
    public int[] solution(String[] id_list, String[] report, int k) {
        threshold = k;
        users = new HashMap<>();
        
        for (String id : id_list) {
            User user = new User();
            users.put(id, user);
        }
        
        for (String r : report) {
            String[] ids = r.split(" ");
            User reporter = users.get(ids[0]);
            User reported = users.get(ids[1]);
            
            if (!reporter.reportIds.contains(ids[1])) {
                reporter.report(ids[1]);
                reported.reported();
            }
        }
        
        int[] answer = new int[id_list.length];
        int idx = 0;
        
        for (String id : id_list) {
            int count = 0;
            
            for (String reportedId : users.get(id).reportIds) {
                User reported = users.get(reportedId);
                if (reported.isBlocked()) {
                    count++;
                }
            }
            
            answer[idx++] = count;
        }
        
        
        return answer;
    }
    
    static class User {
        int reportedCount; // 신고당한 횟수
        HashSet<String> reportIds; // 유저가 신고한 ID
        
        User() {
            reportedCount = 0;
            reportIds = new HashSet<>();
        }
        
        void report(String id) {
            reportIds.add(id);
        }
        
        void reported() {
            reportedCount++;
        }
        
        boolean isBlocked() {
            return reportedCount >= threshold;
        }
    }
}