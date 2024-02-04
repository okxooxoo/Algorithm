import sys
input = sys.stdin.readline

dp = [[1, 0, 0], [0, 1, 0], [1, 1, 1]]
for n in range(3, 100001):
    temp = [0, 0, 0]
    temp[0] = dp[n-1][1] + dp[n-1][2] % 1000000009
    temp[1] = dp[n-2][0] + dp[n-2][2] % 1000000009
    temp[2] = dp[n-3][0] + dp[n-3][1] % 1000000009
    dp.append(temp)

T = int(input())
for _ in range(T):
    n = int(input())
    print(sum(dp[n-1]) % 1000000009)