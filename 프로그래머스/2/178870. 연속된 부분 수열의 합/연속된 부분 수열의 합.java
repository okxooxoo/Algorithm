class Solution {
    public int[] solution(int[] sequence, int k) {
        // 누적합 구하기
        int[] accSum = new int[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            if (i == 0) {
                accSum[i] = sequence[i];
                continue;
            }
            accSum[i] = sequence[i] + accSum[i - 1];
        }
        
        int ansLeft = -1;
        int ansRight = -1;
        int ansD = 1_000_000;
        
        // 투 포인터
        int left = 0;
        int right = 0;
        
        while (right < sequence.length) {
            int sum = accSum[right] - accSum[left] + sequence[left];
            if (sum < k) {
                right++;
            } else if (sum > k) {
                left++;
                
                if (left > right) {
                    right++;
                }
            } else {
                if (ansD > right - left) {
                    ansLeft = left;
                    ansRight = right;
                    ansD = right - left;
                }
                right++;
            }
        }
        
        int[] answer = {ansLeft, ansRight};
        return answer;
    }
}