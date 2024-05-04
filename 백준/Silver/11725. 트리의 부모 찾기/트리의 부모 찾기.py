import sys
sys.setrecursionlimit(10**6)

N = int(input())
tree = [[] for _ in range(N+1)]
parents = [0 for _ in range(N+1)] # 부모 노드

for _ in range(N-1):
    a, b = map(int, input().split())
    tree[a].append(b)
    tree[b].append(a)

visited = [False] * (N+1)

def dfs(node, parent):
    visited[node] = True # 방문 처리
    parents[node] = parent
    for i in tree[node]:
        if not visited[i]:
            dfs(i, node)

dfs(1, 0)

for i in range(2, len(parents)):
    print(parents[i])