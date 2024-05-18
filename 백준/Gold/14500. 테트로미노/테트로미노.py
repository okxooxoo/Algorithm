import sys
input = sys.stdin.readline

N, M = map(int, input().split())
matrix = []
for _ in range(N):
    row = list(map(int, input().rstrip().split()))
    matrix.append(row)

mx = 0
visited = []
def dfs(i, j, s, depth):
    global mx
    # 이미 방문했으면
    if (i, j) in visited:
        return
    # 테트로미노가 완성되면
    if depth == 4:
        if mx < s:
            mx = s
        return
    # 방문 처리
    visited.append((i, j))
    # ㅗ자 모양도 찾아야 함
    if depth == 3:
        prev_i = visited[-2][0]
        prev_j = visited[-2][1]
        if prev_i+1 < N: dfs(prev_i+1, prev_j, s + matrix[prev_i+1][prev_j], depth + 1)
        if prev_i-1 >= 0: dfs(prev_i-1, prev_j, s + matrix[prev_i-1][prev_j], depth + 1)
        if prev_j+1 < M: dfs(prev_i, prev_j+1, s + matrix[prev_i][prev_j+1], depth + 1)
        if prev_j-1 >= 0: dfs(prev_i, prev_j-1, s + matrix[prev_i][prev_j-1], depth + 1)
    # 상하좌우 칸으로 이동
    if i+1 < N: dfs(i+1, j, s + matrix[i+1][j], depth + 1)
    if i-1 >= 0: dfs(i-1, j, s + matrix[i-1][j], depth + 1)
    if j+1 < M: dfs(i, j+1, s + matrix[i][j+1], depth + 1)
    if j-1 >= 0: dfs(i, j-1, s + matrix[i][j-1], depth + 1)
    # 백트래킹
    visited.pop()

for i in range(N):
    for j in range(M):
        dfs(i, j, matrix[i][j], 1)
print(mx)