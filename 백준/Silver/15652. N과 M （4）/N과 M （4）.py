N, M = map(int, input().split())

visited = []

# 1부터 N까지 자연수 중에서 M개를 고른 수열 (중복 허용, 오름차순)
def dfs(start):
    if len(visited) == M:
        print(*visited)
        return
    
    for i in range(start, N+1):
        visited.append(i)
        dfs(i)
        # 백트래킹
        visited.pop()

dfs(1)