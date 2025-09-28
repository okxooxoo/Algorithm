class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {     
        long answer = 0;
        int dPointer = n - 1;
        int pPointer = n - 1;

        while (dPointer >= 0 || pPointer >= 0) {
            // 현재 배달/수거해야 할 가장 먼 집 위치 갱신
            while (dPointer >= 0 && deliveries[dPointer] == 0) dPointer--;
            while (pPointer >= 0 && pickups[pPointer] == 0) pPointer--;

            if (dPointer < 0 && pPointer < 0) break;

            int farthest = Math.max(dPointer, pPointer);
            answer += (farthest + 1) * 2;

            int capRemain = cap;

            // 배달
            for (int i = dPointer; i >= 0 && capRemain > 0; i--) {
                if (deliveries[i] == 0) continue;
                if (deliveries[i] <= capRemain) {
                    capRemain -= deliveries[i];
                    deliveries[i] = 0;
                } else {
                    deliveries[i] -= capRemain;
                    capRemain = 0;
                }
            }

            capRemain = cap;

            // 수거
            for (int i = pPointer; i >= 0 && capRemain > 0; i--) {
                if (pickups[i] == 0) continue;
                if (pickups[i] <= capRemain) {
                    capRemain -= pickups[i];
                    pickups[i] = 0;
                } else {
                    pickups[i] -= capRemain;
                    capRemain = 0;
                }
            }
        }

        return answer;
    }
}
