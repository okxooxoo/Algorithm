import java.util.*;

class Solution {
    private static ArrayList<Long> operand;
    private static ArrayList<Character> operator;
    
    private static char[] opers = {'+', '-', '*'};
    private static char[] operPerm;
    private static boolean[] visited;
    private static long answer = 0;
    
    private static void DFS(int depth) {
        if (depth >= 3) {
            long result = solve();
            answer = Math.max(answer, Math.abs(result));
            return;
        }
        
        for (int i = 0; i < 3; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            
            operPerm[depth] = opers[i];
            DFS(depth + 1);
            
            visited[i] = false;
        }
    }
    
    private static long solve() {
    ArrayList<Long> tempOperand = new ArrayList<>(operand);
    ArrayList<Character> tempOperator = new ArrayList<>(operator);
        
        for (char c : operPerm) {
            int i = 0;
            while (i < tempOperator.size()) {
                if (tempOperator.get(i) == c) {
                    long a = tempOperand.get(i);
                    long b = tempOperand.get(i + 1);
                    long result = calc(a, b, c);

                    tempOperand.remove(i + 1);
                    tempOperand.remove(i);
                    tempOperand.add(i, result);
                    tempOperator.remove(i);
                } else {
                    i++;
                }
            }
        }
        
        return tempOperand.get(0);
    }
    
    private static long calc(long a, long b, char oper) {
        switch (oper) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        
        return -1;
    }
    
    public long solution(String expression) {
        operand = new ArrayList<>();
        operator = new ArrayList<>();
        visited = new boolean[3];
        operPerm = new char[3]; // 현재 연산자의 우선순위
        
        // 연산자와 피연산자 구분
        String num = "";
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                num += c;
            } else {
                operand.add(Long.parseLong(num));
                num = "";
                operator.add(c);
            }
        }
        operand.add(Long.parseLong(num)); // 마지막 숫자 추가
        
        DFS(0);

        return answer;
    }
}