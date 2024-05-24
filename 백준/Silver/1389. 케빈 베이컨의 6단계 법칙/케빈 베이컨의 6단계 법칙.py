from collections import deque
import sys

N, M = map(int, input().split())
graph = [[False for _ in range(N+1)] for _ in range(N+1)]
for _ in range(M):
    A, B = map(int, input().split())
    graph[A][B] = True
    graph[B][A] = True

def bfs(start):
    dq = deque([(start, 0)])
    visited = []
    kevin_bacon = 0
    while dq:
        person, depth = dq.popleft()
        # 이미 방문했으면
        if person in visited:
            continue
        # 방문 처리
        visited.append(person)
        # 케빈 베이컨 수 더하기
        kevin_bacon += depth
        # 모두 방문했으면
        if len(visited) == N:
            break
        for i in range(1, N+1):
            if graph[person][i]:
                dq.append((i, depth + 1))
    return kevin_bacon

mn = sys.maxsize
for i in range(1, N+1):
    kevin_bacon = bfs(i)
    if mn > kevin_bacon:
        mn = kevin_bacon
        result = i
print(result)