from collections import deque

N = int(input())
r1, c1, r2, c2 = map(int, input().split())

board = [[False for _ in range(N)] for _ in range(N)]
moves = [(-2, -1), (-2, 1), (0, -2), (0, 2), (2, -1), (2, 1)]
def bfs(r1, c1, r2, c2):
    dq = deque([(r1, c1, 0)])
    while dq:
        r, c, count = dq.popleft()
        # 도착하면
        if r == r2 and c == c2:
            return count
        # 보드판의 범위를 벗어나면
        if r < 0 or c < 0 \
        or r >= N or c >= N:
            continue
        # 이미 방문했으면
        if board[r][c]:
            continue
        # 방문 처리
        board[r][c] = True
        # 이동
        for move in moves:
            dq.append((r + move[0], c + move[1], count + 1))
    return -1

print(bfs(r1, c1, r2, c2))