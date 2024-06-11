from collections import deque

N = int(input())
graph = []
answer = [[0 for _ in range(N)] for _ in range(N)]

def is_connected(A, B):
    dq = deque([(A, 0)])
    visited = []
    while dq:
        current, depth = dq.popleft()
        # 목표 정점에 도착하면
        if current == B and depth > 0:
            return 1
        # 이미 방문했는지 판단
        if current in visited:
            continue
        # 방문 처리
        visited.append(current)
        # 연결된 정점 방문
        for i in range(N):
            if graph[current][i] == 1:
                dq.append((i, depth + 1))
    return 0

# 입력
for _ in range(N):
    row = list(map(int, input().split()))
    graph.append(row)
# 경로 존재 여부 판단
for i in range(N):
    for j in range(N):
        answer[i][j] = is_connected(i, j)
#출력
for i in range(N):
    print(*answer[i])