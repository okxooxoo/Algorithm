def solution(numbers, target):
    length = len(numbers)
    
    def dfs(index, hap):
        plusHap = hap + numbers[index]
        minusHap = hap - numbers[index]
        
        # 모든 수를 사용해서 타겟 넘버를 만들었으면
        if index == length - 1:
            if plusHap == target or minusHap == target:
                return 1
            else:
                return 0
            
        return dfs(index + 1, plusHap) + dfs(index + 1, minusHap)
    
    answer = dfs(0, 0)
    return answer