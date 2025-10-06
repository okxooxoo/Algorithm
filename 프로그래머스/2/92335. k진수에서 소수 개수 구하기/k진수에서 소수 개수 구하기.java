import java.util.*;

class Solution {
    
    // 특정 진법으로 수 변환
    private static String convertBase(int n, int k) {
        String str = "";
        
        while (true) {
            str += n % k;
            n /= k;
            
            if (n == 0) {
                break;
            }
        }
        
        String reversed = new StringBuilder(str).reverse().toString();
        return reversed;
    }
    
    // 소수인지 여부 판단
    private static boolean isPrime(long num) {
        if (num == 1) return false;
        
        int limit = (int)Math.sqrt(num);
        for (int i = 2; i <= limit; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public int solution(int n, int k) {
        String base = convertBase(n, k);
        long[] nums = Arrays.stream(base.split("0+"))
                            .filter(s -> !s.isEmpty())
                            .mapToLong(Long::parseLong)
                            .toArray();
        
        int answer = 0;
        
        for (long num : nums) {
            if (isPrime(num)) {
                answer++;
            }
        }
        
        return answer;
    }
}