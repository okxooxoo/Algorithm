N, M = map(int, input().split())

visited = []

# 1부터 N까지 자연수 중에서 M개를 고른 수열 (중복 허용)
def dfs():
    if len(visited) == M:
        print(*visited)
        return
    
    for i in range(1, N+1):
        visited.append(i)
        dfs()
        # 백트래킹
        visited.pop()

dfs()