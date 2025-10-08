import java.util.*;

class Solution {
    private static int maxLen;
    private static int maxOrder;
    private static String order;
    private static TreeMap<String, Integer> comb;
    
    private static void makeComb(int index, String curComb) {
        if (curComb.length() == maxLen) {
            if (!comb.containsKey(curComb)) {
                comb.put(curComb, 0);
            }
            
            comb.put(curComb, comb.get(curComb) + 1);
            maxOrder = Math.max(comb.get(curComb), maxOrder);
            return;
        }
        
        if (index >= order.length()) return;
        
        makeComb(index + 1, curComb + order.charAt(index));
        makeComb(index + 1, curComb);
    }
    
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> combList = new ArrayList<>();
        
        for (int l : course) {
            comb = new TreeMap<>();
            maxLen = l;
            maxOrder = 0;
            for (String o : orders) {
                if (l > o.length()) continue;
                
                char[] temp = o.toCharArray();
                Arrays.sort(temp);
                order = new String(temp);
                makeComb(0, "");
            }
            
            for (Map.Entry<String, Integer> entry : comb.entrySet()) {
                if (maxOrder > 1 && entry.getValue() == maxOrder) {
                    combList.add(entry.getKey());
                }
            }
        }
        
        Collections.sort(combList);
        
        String[] answer = combList.toArray(new String[0]);
        return answer;
    }
}