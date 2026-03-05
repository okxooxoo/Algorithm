import java.util.*;

class Solution {
    private static ArrayList<ArrayList<Integer>> graph;
    private static boolean[] visited;
    
    // 홀수노드, 짝수노드: 0 반환 / 역홀수노드, 역짝수노드: 1 반환 / 둘 다 성립: 2 반환
    private static int getStatus(int node) {
        int degree = graph.get(node).size();
        return (node % 2 == degree % 2) ? 1 : 0;
    }
    
    private static int BFS(int node) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(node);
        visited[node] = true;
        
        int nodeCnt = 0;
        int sts = 0;
        
        while (!q.isEmpty()) {
            int cur = q.pollFirst();
            
            nodeCnt++;
            sts += getStatus(cur);
            
            for (int nxt : graph.get(cur)) {
                if (visited[nxt]) continue;
                visited[nxt] = true;
                q.addLast(nxt);
            }
        }
        
        if (nodeCnt == 2) {
            if (node % 2 == graph.get(node).get(0) % 2) return -1;
            return 2;
        }
        if (sts == 1) return 0;
        if (nodeCnt - 1 == sts) return 1;
        return -1;
    }
    
    public int[] solution(int[] nodes, int[][] edges) {
        int V = Arrays.stream(nodes).max().getAsInt();
        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        int[] answer = new int[2];
        visited = new boolean[V + 1];
        
        for (int node : nodes) {
            if (visited[node]) continue;
            int result = BFS(node);
            if (result == -1) continue;
            if (result == 2) {
                answer[0]++;
                answer[1]++;
                continue;
            }
            
            answer[result]++;
        }

        return answer;
    }
}