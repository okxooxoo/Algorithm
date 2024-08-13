from collections import deque

N, M, T = map(int, input().split())
ground = []

for _ in range(N):
    row = list(map(int, input().split()))
    ground.append(row)

def BFS():
    dq = deque([(0, 0, 0, 0)])
    visited = [[[False, False] for _ in range(M)] for _ in range(N)]
    moves = [(1, 0), (-1, 0), (0, 1), (0, -1)] # 상하좌우

    while dq:
        r, c, time, hasGram = dq.popleft()
        # 시간 초과했으면
        if time > T:
            break
        # 공주에게 도착했으면
        if r == N-1 and c == M-1:
            return time
        # 범위를 벗어나면
        if r < 0 or c < 0 \
        or r >= N or c >= M:
            continue
        # 이미 방문했으면
        if visited[r][c][hasGram]:
            continue
        # 방문 처리
        visited[r][c][hasGram] = True
        # 벽에 부딪히면
        if hasGram == 0 and ground[r][c] == 1:
            continue
        # 그람을 얻으면
        if ground[r][c] == 2:
            hasGram = 1
        # 상하좌우로 이동
        for move in moves:
            dq.append((r + move[0], c + move[1], time + 1, hasGram))

    return "Fail"
        

print(BFS())