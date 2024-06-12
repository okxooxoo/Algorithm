N = int(input())
dp = [(0, 0, 0)]
for i in range(1, N+1):
    red, green, blue = map(int, input().split())
    red += min(dp[i-1][1], dp[i-1][2])
    green += min(dp[i-1][0], dp[i-1][2])
    blue += min(dp[i-1][0], dp[i-1][1])
    dp.append((red, green, blue))
print(min(dp[N]))