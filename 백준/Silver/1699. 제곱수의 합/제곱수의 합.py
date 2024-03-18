import math
N = int(input())

dp = [i for i in range(N + 1)] # 인덱스로 초기화
# 제곱수마다 1 저장
for i in range(1, int(math.sqrt(N)) + 1):
    dp[i ** 2] = 1
# N을 이루는 제곱수를 모두 고려하여 최솟값 찾음
for i in range(1, N + 1):
    for j in range(1, int(math.sqrt(i)) + 1):
        if dp[i] > dp[i - j ** 2] + 1:
            dp[i] = dp[i - j ** 2] + 1

print(dp[N])