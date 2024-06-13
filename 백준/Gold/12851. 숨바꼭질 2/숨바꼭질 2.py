from collections import deque

N, K = map(int, input().split())

def bfs(N, K):
    dq = deque([(N, 0)])
    visited = [100001] * 100001
    count = 0
    while dq:
        X, time = dq.popleft()
        # 동생을 찾으면
        if X == K:
            count += 1
            while dq:
                X_, time_ = dq.popleft()
                if X_ == X and time_ == time:
                    count += 1
            break
        # 범위를 벗어나면
        if X < 0 or X > 100000:
            continue
        # 이미 방문했으면
        if visited[X] < time:
            continue
        # 방문 처리
        visited[X] = time
        # 이동
        dq.append((X-1, time+1))
        dq.append((X+1, time+1))
        dq.append((2*X, time+1))
    print(time)
    print(count)

bfs(N, K)