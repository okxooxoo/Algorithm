import java.util.*;

// 생성된 노드: 진입 차수 = 0, 진출 차수 >= 2
// 도넛 모양 그래프: 모든 노드가 진입차수 = 1, 진출 차수 = 1
// 막대 모양 그래프: 시작 노드가 진입차수 = 0, 진출 차수 = 0 or 1
// 8자 모양 그래프: 중간 노드가 진입 차수 = 2, 진출 차수 = 2

class Solution {   
    int[][] degrees;
    int graphCount;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        degrees = new int[1_000_001][2];
        graphCount = 0;
        
        // 모든 노드의 진입/진출 차수 세기
        for (int i = 0; i < edges.length; i++) {
            degrees[edges[i][0]][1]++;
            degrees[edges[i][1]][0]++;
        }
        
        // 생성된 노드 찾기
        for (int i = 1; i <= 1_000_000; i++) {
            if (degrees[i][0] == 0 && degrees[i][1] >= 2) {
                answer[0] = i; // 생성된 노드 번호
                graphCount = degrees[i][1];
                break;
            }
        }
        
        // 사이즈 1인 Bar가 있을 수 있으므로 후보에 저장
        ArrayList<Integer> candidates = new ArrayList<>();
        
        int bar = 0;
        int eight = 0;
        int donut = 0;
        
        // 생성된 노드 제외하고 모든 노드의 진입/진출 차수 세기
        degrees = new int[1_000_001][2];
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][0] == answer[0]) {
                candidates.add(edges[i][1]);
                
                continue;
            }
            
            degrees[edges[i][0]][1]++;
            degrees[edges[i][1]][0]++;
        }
        
        // 그래프 개수 찾기
        for (int i = 1; i <= 1_000_000; i++) {
            if (degrees[i][0] == 0 && degrees[i][1] == 1) {
                bar++;
            } else if (degrees[i][0] == 2) {
                eight++;
            }
        }
        
        // 사이즈 1인 Bar 찾기
        for (int n : candidates) {
            if (degrees[n][0] == 0 && degrees[n][1] == 0) {
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