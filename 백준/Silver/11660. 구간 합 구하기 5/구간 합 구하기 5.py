import sys
input = sys.stdin.readline

N, M = map(int, input().split())
table = [[0] * (N+1)]
for _ in range(N):
    row = list(map(int, input().split()))
    table.append([0] + row)
# 누적합 구하기
for i in range(1, N+1):
    for j in range(1, N+1):
        table[i][j] += table[i][j-1]
        table[i][j] += table[i-1][j]
        table[i][j] -= table[i-1][j-1]
for _ in range(M):
    x1, y1, x2, y2 = map(int, input().split())
    s = table[x2][y2] + table[x1-1][y1-1] - table[x2][y1-1] - table[x1-1][y2]
    print(s)
    