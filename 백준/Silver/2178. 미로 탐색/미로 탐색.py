from collections import deque

N, M = map(int, input().split())
graph = [list(map(int, input())) for _ in range(N)]

def bfs():
    dq = deque([(0, 0, 1)])
    while dq:
        x, y, count = dq.popleft()
        # 미로의 범위를 벗어나면
        if x < 0 or y < 0 \
        or x >= M or y >= N:
            continue
        # 이동할 수 없는 칸이면
        if graph[y][x] == 0:
            continue
        # 도착했으면
        if x == M - 1 and y == N - 1:
            return count
        # 방문 처리
        graph[y][x] = 0
        # 상하좌우로 이동
        dq.append((x - 1, y, count + 1))
        dq.append((x + 1, y, count + 1))
        dq.append((x, y - 1, count + 1))
        dq.append((x, y + 1, count + 1))

print(bfs())