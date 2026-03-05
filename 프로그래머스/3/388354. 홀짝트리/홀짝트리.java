import java.util.*;

class Solution {
    private static HashMap<Integer, ArrayList<Integer>> graph;
    private static HashSet<Integer> visited;
    private static int[] answer;
    
    private static int oddOrEven;
    private static int reverOddOrEven;
    
    private static int getStatus(int node) {
        int degree = graph.get(node).size();
        return (node % 2 == degree % 2) ? reverOddOrEven++ : oddOrEven++;
    }
    
    private static void BFS(int node) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(node);
        visited.add(node);
        
        // 초기화
        oddOrEven = 0;
        reverOddOrEven = 0;
        
        while (!q.isEmpty()) {
            int cur = q.pollFirst();
            
            getStatus(cur);
            
            for (int nxt : graph.get(cur)) {
                if (visited.contains(nxt)) continue;
                visited.add(nxt);
                q.addLast(nxt);
            }
        }
        
        if (oddOrEven == 1) answer[1]++;
        if (reverOddOrEven == 1) answer[0]++;
    }
    
    public int[] solution(int[] nodes, int[][] edges) {
        // 그래프 초기화
        graph = new HashMap<>();
        for (int node : nodes) {
            graph.put(node, new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        answer = new int[2];
        visited = new HashSet<>();
        
        for (int node : nodes) {
            if (visited.contains(node)) continue;
            BFS(node);
        }

        return answer;
    }
}