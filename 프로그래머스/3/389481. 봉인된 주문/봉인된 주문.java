import java.util.*;

class Solution {

    // 문자열 → 숫자 (1-based)
    private static long strToNum(String s) {
        long num = 0;
        for (char c : s.toCharArray()) {
            num = num * 26 + (c - 'a' + 1);
        }
        return num;
    }

    // 숫자 → 문자열
    private static String numToStr(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            num--;
            sb.append((char) ('a' + (num % 26)));
            num /= 26;
        }
        return sb.reverse().toString();
    }

    public String solution(long n, String[] bans) {

        long[] banNums = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            banNums[i] = strToNum(bans[i]);
        }
        Arrays.sort(banNums);

        long left = n, right = n + bans.length;

        while (left < right) {
            long mid = (left + right) / 2;

            int banned = 0;
            for (long b : banNums) {
                if (b <= mid) banned++;
                else break;
            }

            if (mid - banned >= n) right = mid;
            else left = mid + 1;
        }

        return numToStr(left);
    }
}