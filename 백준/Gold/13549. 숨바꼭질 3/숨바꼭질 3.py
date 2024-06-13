from collections import deque

N, K = map(int, input().split())

def bfs(N, K):
    mn_time = 100001
    dq = deque([(N, 0)])
    visited = [False] * 100001
    while dq:
        X, time = dq.popleft()
        # 동생을 찾으면
        if X == K:
            return time
        # 범위를 벗어나면
        if X < 0 or X > 100000:
            continue
        # 이미 방문했으면
        if visited[X]:
            continue
        # 방문 처리
        visited[X] = True
        # 이동
        dq.append((X-1, time+1))
        dq.append((X+1, time+1))
        dq.appendleft((2*X, time))

print(bfs(N, K))