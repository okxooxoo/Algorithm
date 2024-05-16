from collections import deque

N, K = map(int, input().split())
def bfs(position, dest):
    visited = [False] * 100001
    dq = deque([(position, 0)])
    while dq:
        current, time = dq.popleft() # 현재 위치와 경과 시간
        # 도착했으면
        if current == dest:
            return time
        # 범위를 벗어나면
        if current < 0 or current > 100000:
            continue
        # 이미 방문했으면
        if visited[current]:
            continue
        # 방문 처리
        visited[current] = True
        # 위치 이동
        dq.append((current - 1, time + 1))
        dq.append((current + 1, time + 1))
        dq.append((current * 2, time + 1))
print(bfs(N, K))