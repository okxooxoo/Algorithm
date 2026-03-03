class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        for (int i = 1; i <= s.length() / 2; i++) {
            String result = "";
            String prev = s.substring(0, i);
            int cnt = 1;
            int j;
            
            for (j = i; j + i <= s.length(); j += i) {    
                String sub = s.substring(j, i + j);
                if (prev.equals(sub)) {
                    cnt++;
                } else {
                    result += (cnt > 1 ? cnt : "") + prev;
                    prev = sub;
                    cnt = 1;
                }
            }
            
            result += (cnt > 1 ? cnt : "") + prev;
            result += s.substring(j);
            
            answer = Math.min(answer, result.length());
        }
        
        return answer;
    }
}