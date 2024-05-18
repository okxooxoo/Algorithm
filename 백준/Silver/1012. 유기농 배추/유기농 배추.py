from collections import deque

def bfs(x, y):
    dq = deque([(x, y)])
    while dq:
        x, y = dq.popleft()
        # 범위를 벗어나면
        if x < 0 or y < 0 \
        or x >= M or y >= N:
            continue
        # 배추가 없으면
        if ground[y][x] == 0:
            continue
        # 방문 처리
        ground[y][x] = 0
        # 상하좌우 방문
        dq.append((x+1, y))
        dq.append((x-1, y))
        dq.append((x, y+1))
        dq.append((x, y-1))

T = int(input())
for _ in range(T):
    M, N, K = map(int, input().split())
    ground = [[0 for _ in range(M)] for _ in range(N)]
    for _ in range(K):
        X, Y = map(int, input().split())
        ground[Y][X] = 1
    count = 0
    for y in range(N):
        for x in range(M):
            if ground[y][x] == 1:
                bfs(x, y)
                count += 1
    print(count)