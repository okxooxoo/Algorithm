from collections import deque

M, N = map(int, input().split())
box = [list(map(int, input().split())) for _ in range(N)]

def bfs():
    seignant = 0
    dq = deque([])
    # 익은 토마토 데크에 넣기
    for i in range(N):
        for j in range(M):
            if box[i][j] == 1:
                dq.append((i, j, 0, True))
            elif box[i][j] == 0:
                # 덜 익은 토마토 개수 세기
                seignant += 1
    while dq:
        x, y, day, start = dq.popleft()
        # 상자의 범위를 벗어나면
        if x < 0 or y < 0 \
        or x >= N or y >= M:
            continue
        # 토마토가 없으면
        if box[x][y] == -1:
            continue
        # 토마토가 익었으면
        if box[x][y] == 1 and not start:
            continue
        # 토마토가 안 익었으면
        if box[x][y] == 0:
            box[x][y] = 1
            seignant -= 1
        # 모든 토마토가 익었는지 확인
        if seignant == 0:
            return day
        # 상하좌우 토마토 데크에 넣기
        dq.append((x - 1, y, day + 1, False))
        dq.append((x + 1, y, day + 1, False))
        dq.append((x, y - 1, day + 1, False))
        dq.append((x, y + 1, day + 1, False))

    return -1

print(bfs())