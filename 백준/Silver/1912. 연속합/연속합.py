n = int(input())
P = list(map(int, input().split()))

dp = [P[0]] + [0] * (n - 1)
for i in range(1, n):
    if dp[i-1] < 0: # 이전의 연속합이 음수면
        dp[i] = P[i]
    else: # 이전의 연속합이 양수면
        if dp[i-1] + P[i] >= 0:
            dp[i] = dp[i-1] + P[i]
        else:
            dp[i] = P[i]

print(max(dp))