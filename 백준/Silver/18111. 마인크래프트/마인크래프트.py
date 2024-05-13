import sys
input = sys.stdin.readline

N, M, B = map(int, input().split())
ground = []
mx = 0
mn = 256
for _ in range(N):
    row = list(map(int, input().split()))
    mx = max(mx, max(row))
    mn = min(mn, min(row))
    ground.append(row)

# 최저 높이에서 최고 높이까지
mn_time = sys.maxsize
high = 0
for i in range(mn, mx+1):
    time = 0
    inventory = B
    for n in range(N):
        for m in range(M):
            if ground[n][m] >= i:
                time += (ground[n][m] - i) * 2
                inventory += ground[n][m] - i
            else:
                time += i - ground[n][m]
                inventory -= i - ground[n][m]
    if inventory < 0:
        break
    if mn_time < time:
        break
    mn_time = time
    high = i
print(mn_time, high)
