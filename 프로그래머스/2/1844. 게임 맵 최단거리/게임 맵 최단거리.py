from collections import deque

def solution(maps):
    n = len(maps)
    m = len(maps[0])
    answer = bfs(0, 0, n, m, maps)
    return answer

def bfs(r, c, n, m, maps):
    # (r, c, 1)는 현재 위치와 이동한 거리
    q = deque([(r, c, 1)])
    # 이동 방향 정의
    directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]
    while q:
        a, b, dist = q.popleft()
        # 도착한 경우
        if a == n-1 and b == m-1:
            return dist
        # 지도의 끝인 경우
        if a < 0 or b < 0 \
        or a >= n or b >= m:
            continue
        # 벽인 경우
        if maps[a][b] == 0:
            continue
        # 방문 처리(벽으로 막기)
        maps[a][b] = 0
        # 상하좌우로 이동
        for x, y in directions:
            q.append((a + x, b + y, dist + 1))
    return -1
            