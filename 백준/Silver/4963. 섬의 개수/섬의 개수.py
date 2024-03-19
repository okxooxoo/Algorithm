import sys
sys.setrecursionlimit(10000)
input = sys.stdin.readline

# 깊이 우선 탐색
def dfs(x, y):
    if x < 0 or y < 0 or x >= h or y >= w:
        return False
    if graph[x][y] == 0:
        return False
    # 방문 처리
    graph[x][y] = 0
    # 인접한 사각형으로 이동
    dfs(x-1, y-1)
    dfs(x-1, y)
    dfs(x-1, y+1)
    dfs(x, y-1)
    dfs(x, y+1)
    dfs(x+1, y-1)
    dfs(x+1, y)
    dfs(x+1, y+1)
    return True

while True:
    w, h = map(int, input().split())

    if w == 0 and h == 0:
        break
    
    # 지도를 입력받음
    graph = []
    for _ in range(h):
        graph.append(list(map(int, input().split())))

    count = 0
    for x in range(h):
        for y in range(w):
            if dfs(x, y) == True:
                count += 1
    print(count)
