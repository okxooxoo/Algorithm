N, M = map(int, input().split())
board = [list(input()) for _ in range(N)] # 게임판
moves = [(-1, 0), (1, 0), (0, -1), (0, 1)]
visited = []
is_cycle = False # 사이클 존재 여부

def dfs(a, b, depth, color):
    global is_cycle

    # 게임판의 범위를 벗어나면
    if a < 0 or b < 0 \
    or a >= N or b >= M:
        return
    # 같은 색이 아니면
    if board[a][b] != color:
        return
    # 사이클을 이루면
    if depth >= 5 and a == visited[0][0] and b == visited[0][1]:
        is_cycle = True
        return
    # 방문했으면
    if (a, b) in visited:
        return
    # 방문 처리
    visited.append((a, b))
    # 상하좌우로 이동
    for move in moves:
        dfs(a + move[0], b + move[1], depth + 1, color)
        if is_cycle:
            return
    # 백트래킹
    visited.pop()

# 모든 점에 대하여 탐색 시작
for i in range(N):
    for j in range(M):
        dfs(i, j, 1, board[i][j])
        if is_cycle:
            break
    if is_cycle:
        break
print('Yes' if is_cycle else 'No')