from collections import deque

N = int(input())
picture1 = []
picture2 = [] # 적록색약이 보는 그림
for _ in range(N):
    row = input()
    picture1.append(list(row))
    picture2.append(list(row.replace('R', 'G')))

def bfs(start, color, picture, visited):
    dq = deque([start])
    while dq:
        r, c = dq.popleft()
        # 그림의 영역을 벗어나면
        if r < 0 or c < 0 \
        or r >= N or c >= N:
            continue
        # 색상이 다르면
        if picture[r][c] != color:
            continue
        # 이미 방문했으면
        if visited[r][c]:
            continue
        # 방문 처리
        visited[r][c] = True
        # 상하좌우 방문
        dq.append((r+1, c))
        dq.append((r-1, c))
        dq.append((r, c+1))
        dq.append((r, c-1))

count1 = 0
count2 = 0
visited1 = [[False for _ in range(N)] for _ in range(N)]
visited2 = [[False for _ in range(N)] for _ in range(N)]
for i in range(N):
    for j in range(N):
        if not visited1[i][j]:
            bfs((i, j), picture1[i][j], picture1, visited1)
            count1 += 1
        if not visited2[i][j]:
            bfs((i, j), picture2[i][j], picture2, visited2)
            count2 += 1
print(count1, count2)