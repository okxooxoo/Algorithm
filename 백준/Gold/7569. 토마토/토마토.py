from collections import deque

def bfs(ripe):
    visited = [[[False for _ in range(M)] for _ in range(N)] for _ in range(H)]
    moves = [(0, 0, 1), (0, 0, -1), (0, 1, 0), (0, -1, 0), (1, 0, 0), (-1, 0, 0)]
    dq = deque(ripe)
    day = 0
    while dq:
        m, n, h, depth = dq.popleft()
        # 범위를 벗어나면
        if m < 0 or n < 0 or h < 0 \
        or m >= M or n >= N or h >= H:
            continue
        # 방문했으면
        if visited[h][n][m]:
            continue
        # 방문 처리
        visited[h][n][m] = True
        # 토마토가 없으면
        if storage[h][n][m] == -1:
            continue
        # 토마토가 안 익었으면
        if storage[h][n][m] == 0:
            storage[h][n][m] = 1
            day = depth
        # 인접한 곳 순회
        for move in moves:
            dq.append((m + move[0], n + move[1], h + move[2], depth + 1))

    for h in range(H):
        for n in range(N):
            for m in range(M):
                if storage[h][n][m] == 0:
                    return -1
    return day

M, N, H = map(int, input().split())
storage = [] # 3차원 배열 형식의 토마토 창고
ripe = []
for h in range(H):
    box = []
    for n in range(N):
        row = list(map(int, input().split()))
        for m in range(M):
            if row[m] == 1: # 토마토가 처음부터 익었으면
                ripe.append((m, n, h, 0))
        box.append(row)
    storage.append(box)

print(bfs(ripe))