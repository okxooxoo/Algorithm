from collections import deque

N, M = map(int, input().split())
campus = []
I = (0, 0)
for i in range(N):
    row = list(input())
    for j in range(M):
        if row[j] == 'I':
            I = (i, j)
            break
    campus.append(row)

visited = [[False for _ in range(M)] for _ in range(N)]
def bfs(I):
    dq = deque([I])
    count = 0
    while dq:
        i, j = dq.popleft()
        # 범위를 벗어나면
        if i < 0 or j < 0 \
        or i >= N or j >= M:
            continue
        # 벽에 닿으면
        if campus[i][j] == 'X':
            continue
        # 이미 방문했으면
        if visited[i][j]:
            continue
        # 방문 처리
        visited[i][j] = True
        # 사람을 만나면
        if campus[i][j] == 'P':
            count += 1
        # 상하좌우로 이동
        dq.append((i + 1, j))
        dq.append((i - 1, j))
        dq.append((i, j + 1))
        dq.append((i, j - 1))
    return count

count = bfs(I)
if count == 0:
    print('TT')
else:
    print(count)