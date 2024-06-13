N, K = map(int, input().split())
objects = []
for _ in range(N):
    W, V = map(int, input().split())
    objects.append((W, V))

dp = [0] * (K+1)
for weight, value in objects:
    for i in range(K, weight-1, -1):
        dp[i] = max(dp[i], dp[i-weight] + value)
print(dp[K])