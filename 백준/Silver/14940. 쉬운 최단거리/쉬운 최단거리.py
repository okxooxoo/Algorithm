from collections import deque

n, m = map(int, input().split())
inputs = []
for i in range(n):
    row = list(map(int, input().split()))
    for j in range(m):
        if row[j] == 2:
            dest = (i, j) # 목표 지점
    inputs.append(row)

visited = [[False for _ in range(m)] for _ in range(n)]
outputs = [[0 for _ in range(m)] for _ in range(n)]

def bfs(dest):
    dq = deque([(*dest, 0)])
    while dq:
        i, j, distance = dq.popleft()
        # 지도의 범위를 벗어나면
        if i < 0 or j < 0 \
        or i >= n or j >= m:
            continue
        # 이미 방문한 곳이면
        if visited[i][j]:
            continue
        # 방문 처리
        visited[i][j] = True
        # 갈 수 없는 땅이면
        if inputs[i][j] == 0:
            continue
        # 갈 수 있는 땅이면
        outputs[i][j] = distance
        # 상하좌우로 이동
        dq.append((i + 1, j, distance + 1))
        dq.append((i - 1, j, distance + 1))
        dq.append((i, j + 1, distance + 1))
        dq.append((i, j - 1, distance + 1))

bfs(dest)

for i in range(n):
    for j in range(m):
        if inputs[i][j] > 0 and not visited[i][j]:
            print(-1, end=' ')
        else:
            print(outputs[i][j], end=' ')
    print()