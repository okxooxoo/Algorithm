N, M = map(int, input().split())

visited = []

# 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 (오름차순)
def dfs(start):
    if len(visited) == M:
        print(*visited)
        return
    
    for i in range(start, N+1):
        if i not in visited:
            visited.append(i)
            dfs(i+1)
            # 백트래킹
            visited.pop()

dfs(1)