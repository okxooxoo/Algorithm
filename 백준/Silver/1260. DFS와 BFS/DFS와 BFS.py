from collections import deque

N, M, V = map(int, input().split())
# 연결되지 않았다는 의미로 False로 초기화
graph = [[False] * (N + 1) for _ in range(N + 1)]
for _ in range(M):
    a, b = map(int, input().split())
    graph[a][b] = True
    graph[b][a] = True

searched = [] # 방문한 노드
# 깊이 우선 탐색
def dfs(V):
    searched.append(V)
    for i in range(1, N+1):
        if graph[V][i] and i not in searched:
            dfs(i)
dfs(V)
print(*searched)

searched = [V] # 방문한 노드
# 너비 우선 탐색
def bfs(V):
    q = deque([V])
    while q:
        current = q.popleft()
        for i in range(1, N+1):
            if graph[current][i] and i not in searched:
                searched.append(i)
                q.append(i)
bfs(V)
print(*searched)