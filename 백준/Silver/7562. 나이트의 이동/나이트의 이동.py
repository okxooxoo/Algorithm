from collections import deque
    
moves = [(-2, -1), (-2, 1), (-1, -2), (-1, 2), (1, -2), (1, 2), (2, -1), (2, 1)]

def bfs(current, dest):
    dq = deque([(*current, 0)])
    visited = set()
    while dq:
        x, y, count = dq.popleft()
        # 도착하면
        if x == dest[0] and y == dest[1]:
            return count
        # 체스판의 범위를 벗어나면
        if x < 0 or y < 0 \
        or x >= I or y >= I:
            continue
        # 이미 방문했으면
        if (x, y) in visited:
            continue
        # 방문 처리
        visited.add((x, y))
        # 나이트 이동
        for i in range(len(moves)):
            dq.append((x + moves[i][0], y + moves[i][1], count + 1))

T = int(input())
for _ in range(T):
    I = int(input())
    current = list(map(int, input().split()))
    dest = list(map(int, input().split()))
    print(bfs(current, dest))