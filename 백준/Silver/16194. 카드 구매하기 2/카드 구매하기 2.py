N = int(input())
P = [0] + list(map(int, input().split()))
dp = [0] + [10000] * N # dp[i]는 i개의 카드를 구매하기 위한 가격의 최솟값

for i in range(1, N+1):
    for j in range(1, i+1):
        dp[i] = min(dp[i], dp[i-j] + P[j])
print(dp[N])