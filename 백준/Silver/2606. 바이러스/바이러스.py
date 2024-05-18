from collections import deque

N = int(input())
M = int(input())
graph = [[] for _ in range(N+1)]
for _ in range(M):
    A, B = map(int, input().split())
    graph[A].append(B)
    graph[B].append(A)

def bfs(start):
    dq = deque([start])
    visited = [False] * (N+1)
    count = -1
    while dq:
        node = dq.popleft()
        # 이미 방문했으면
        if visited[node]:
            continue
        # 방문 처리
        visited[node] = True
        count += 1
        # 인접한 노드 방문
        for i in graph[node]:
            dq.append(i)
    return count

print(bfs(1))