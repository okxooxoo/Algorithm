import sys
sys.setrecursionlimit(10000)
input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[] for _ in range(N+1)]

for _ in range(M):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)

# 깊이 우선 탐색
def dfs(V):
    visited.add(V)
    for node in graph[V]:
        if node not in visited:
            dfs(node)

count = 0
visited = set() # 방문한 모든 노드
for i in range(1, N+1):
    if i in visited:
        continue
    dfs(i)
    count += 1
print(count)