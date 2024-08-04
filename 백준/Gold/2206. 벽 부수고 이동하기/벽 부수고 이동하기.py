from collections import deque

N, M = map(int, input().split())
matrix = []
for _ in range(N):
    matrix.append(list(map(int, list(input()))))

def bfs(start_row, start_col, end_row, end_col):
    dq = deque([(start_row, start_col, 1, 0)])
    moves = [(1, 0), (-1, 0), (0, 1), (0, -1)]
    # index=0은 벽을 부순 적 없을 때의 방문 여부, index=1은 벽을 부순 적 있을 때의 방문 여부
    visited = [[[False, False] for _ in range(M)] for _ in range(N)]

    while dq:
        # 현재 좌표, 이동 횟수, 벽 부쉈는지 여부
        row, col, count, break_wall = dq.popleft()
        # 맵의 범위를 벗어나면
        if row < 0 or col < 0 \
        or row >= N or col >= M:
            continue
        # 도착했으면
        if row == end_row and col == end_col:
            return count
        # 이미 방문했으면
        if visited[row][col][break_wall]:
            continue
        # 방문 처리
        visited[row][col][break_wall] = True
        # 벽이 있으면
        if matrix[row][col] == 1:
            # 이미 벽을 부순 적이 있으면
            if break_wall == 1:
                continue
            # 벽을 부순 적이 없으면
            for move in moves:
                dq.append((row + move[0], col + move[1], count + 1, 1))
        # 벽이 없으면
        if matrix[row][col] == 0:
            for move in moves:
                dq.append((row + move[0], col + move[1], count + 1, break_wall))

    return -1

print(bfs(0, 0, N-1, M-1))