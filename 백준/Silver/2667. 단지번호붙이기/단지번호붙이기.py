N = int(input())
graph = []
for _ in range(N):
    graph.append(list(map(int, input())))

def dfs(x, y):
    # 지도의 범위를 벗어나면
    if x < 0 or y < 0 \
    or x >= N or y >= N:
        return 0
    # 집이 없으면
    if graph[x][y] == 0:
        return 0
    # 방문 처리
    graph[x][y] = 0
    # 상하좌우로 이동
    count = 1
    count += dfs(x - 1, y)
    count += dfs(x + 1, y)
    count += dfs(x, y - 1)
    count += dfs(x, y + 1)
    return count

answers = []
for i in range(N):
    for j in range(N):
        count = dfs(i, j)
        if count > 0:
            answers.append(count)

print(len(answers))
for answer in sorted(answers):
    print(answer)