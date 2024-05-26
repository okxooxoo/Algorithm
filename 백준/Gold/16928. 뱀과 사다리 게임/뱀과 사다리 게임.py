from collections import deque

N, M = map(int, input().split())
board = [i for i in range(101)]
for _ in range(N):
    x, y = map(int, input().split())
    board[x] = y
for _ in range(M):
    u, v = map(int, input().split())
    board[u] = v

visited = [False] * 101
def bfs(start):
    dq = deque([(start, 0)])
    while dq:
        position, depth = dq.popleft()
        # 범위를 벗어나면
        if position < 0 or position > 100:
            continue
        # 도착하면
        if position == 100:
            return depth
        # 이미 방문했으면
        if visited[position]:
            continue
        # 방문 처리
        visited[position] = True
        # 뱀 혹은 사다리가 존재하면 이동
        position = board[position]
        # 주사위 굴리기
        for i in range(1, 7):
            dq.append((position + i, depth + 1))
print(bfs(1))