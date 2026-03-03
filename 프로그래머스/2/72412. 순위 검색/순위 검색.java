import java.util.*;

class Solution {
    private static HashMap<String, ArrayList<Integer>> map;
    
    private static void DFS(String[] str, String key, int depth) {
        if (depth == 4) {
            if (map.containsKey(key)) {
                map.get(key).add(Integer.parseInt(str[depth]));
            } else {
                map.put(key, new ArrayList<>());
                map.get(key).add(Integer.parseInt(str[depth]));
            }
            return;
        }
        
        DFS(str, key + str[depth], depth + 1);
        DFS(str, key + "-", depth + 1);
    }
    
    private static int binarySearch(String key, int score) {
        if (!map.containsKey(key)) return 0;
        
        ArrayList<Integer> list = map.get(key);
        int left = 0;
        int right = list.size();
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            if (list.get(mid) >= score) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return list.size() - left;
    }
    
    public int[] solution(String[] info, String[] query) {
        map = new HashMap<>();
        
        for (String str : info) {
            DFS(str.split(" "), "", 0);
        }
        
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        
        int[] answer = new int[query.length];
        
        for (int i = 0; i < query.length; i++) {
            String[] str = query[i].replace(" and ", "").split(" ");
            String key = str[0];
            int score = Integer.parseInt(str[1]);
            answer[i] = binarySearch(key, score);
        }
        
        return answer;
    }
}