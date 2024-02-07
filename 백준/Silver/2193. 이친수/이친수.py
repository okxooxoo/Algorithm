dp = [0, 1, 1]

for i in range(3, 91):
    dp.append(dp[i-1] + dp[i-2])

N = int(input())
print(dp[N])