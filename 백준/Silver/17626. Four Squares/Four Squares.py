import math

n = int(input())
dp = [0] * (n+1)
for i in range(1, int(math.sqrt(n))+1):
    dp[i**2] = 1

for i in range(1, n+1):
    if dp[i] == 1:
        continue
    mn = i
    for j in range(1, int(math.sqrt(i))+1):
        temp = 1 + dp[i-j**2]
        if mn > temp:
            mn = temp
    dp[i] = mn
print(dp[n])