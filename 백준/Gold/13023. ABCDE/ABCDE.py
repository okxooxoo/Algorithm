N, M = map(int, input().split())
graph = [[] for _ in range(N)] # 시간 복잡도를 낮추기 위해 인접 리스트로 구현

for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

# 깊이 우선 탐색
def dfs(V, visited, depth):
    global result
    if depth == 4:
        result = 1
        return
    
    visited.append(V)
    for node in graph[V]:
        if node not in visited:
            dfs(node, visited, depth + 1)
    visited.pop()

for i in range(N):
    result = 0
    visited = []
    dfs(i, visited, 0)
    if result == 1:
        break
print(result)