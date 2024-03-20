def solution(n, computers):
    visited = []
    def dfs(i):
        if i in visited:
            return False
        visited.append(i)
        for j in range(n):
            if computers[i][j] == 1:
                dfs(j)
        return True

    answer = 0
    for i in range(n):
        if dfs(i) == True:
            answer += 1
    return answer