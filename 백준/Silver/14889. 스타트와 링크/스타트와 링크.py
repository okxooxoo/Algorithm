import sys
input = sys.stdin.readline

N = int(input())
# 능력치 표
ability_table = [list(map(int, input().split())) for _ in range(N)]

# 능력치 차이의 최솟값을 출력(목표)
visited = [False] * N
min_diff = sys.maxsize

def dfs(depth, idx):
    global min_diff

    if depth == N // 2:
        diff = calc_diff()
        if min_diff > diff:
            min_diff = diff
        return

    for i in range(idx, N):
        if not visited[i]:
            visited[i] = True
            dfs(depth + 1, i + 1)
            visited[i] = False

def calc_diff():
    start_ability = 0
    link_ability = 0
    for i in range(N):
        for j in range(N):
            if visited[i] and visited[j]:
                start_ability += ability_table[i][j]
            elif not visited[i] and not visited[j]:
                link_ability += ability_table[i][j]

    diff = abs(start_ability - link_ability)
    return diff

dfs(0, 0)
print(min_diff)