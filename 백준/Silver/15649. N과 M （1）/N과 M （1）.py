N, M = map(int, input().split())

visited = []

# 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 (1 ≤ M ≤ N ≤ 8)
def dfs():
    if len(visited) == M:
        print(*visited)
        return
    
    for i in range(1, N+1):
        if i not in visited:
            visited.append(i)
            dfs()
            # 백트래킹
            visited.pop()

dfs()