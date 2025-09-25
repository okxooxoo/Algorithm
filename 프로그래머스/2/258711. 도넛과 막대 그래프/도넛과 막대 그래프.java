import java.util.*;

// 생성된 노드: 진입 차수 = 0, 진출 차수 >= 2
// 도넛 모양 그래프: 모든 노드가 진출 차수 = 1
// 막대 모양 그래프: 마지막 노드가 진출 차수 = 0
// 8자 모양 그래프: 중간 노드가 진출 차수 = 2

class Solution {   
    int[][] degrees;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        degrees = new int[1_000_001][2];
        
        // 모든 노드의 진입/진출 차수 세기
        for (int i = 0; i < edges.length; i++) {
            degrees[edges[i][0]][1]++;
            degrees[edges[i][1]][0]++;
        }
        
        int graphCount = 0;
        int bar = 0;
        int eight = 0;
        int donut = 0;
        
        // 생성된 노드와 그래프 찾기
        for (int i = 1; i <= 1_000_000; i++) {
            if (degrees[i][1] >= 2) {
                if (degrees[i][0] == 0) {
                    answer[0] = i;
                    graphCount = degrees[i][1];
                } else {
                    eight++;
                }
            } else if (degrees[i][0] >= 1 && degrees[i][1] == 0) {
                bar++;
            }
        }
            
        donut = graphCount - bar - eight;

        
        answer[1] = donut;
        answer[2] = bar;
        answer[3] = eight;
        
        return answer;
    }
}