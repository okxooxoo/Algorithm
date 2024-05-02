import sys
sys.setrecursionlimit(1000000)
input = sys.stdin.readline

# 그룹을 -1, 1로 나눔, 방문하지 않았으면 0
def dfs(node, group):
    # 방문 처리
    visited[node] = group
    # 인접 노드 순회
    for i in graph[node]:
        # 방문하지 않았으면
        if visited[i] == 0:
            is_bipartite = dfs(i, -group)
            if not is_bipartite:
                return False
        # 방문했으면
        else:
            if visited[i] == group:
                return False
    return True


K = int(input())
for _ in range(K):
    V, E = map(int, input().split())
    graph = [[] for _ in range(V+1)] # 인접 리스트
    visited = [0] * (V+1) # 방문 여부
    for _ in range(E):
        u, v = map(int, input().split())
        graph[u].append(v)
        graph[v].append(u)
    for i in range(1, V+1):
        if visited[i] == 0:
            is_bipartite = dfs(i, 1)
            if not is_bipartite:
                break
    print("YES" if is_bipartite else "NO")