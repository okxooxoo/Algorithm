A = ' ' + input()
B = ' ' + input()
dp = [[0 for _ in range(len(B))] for _ in range(len(A))]
for i in range(1, len(A)):
    for j in range(1, len(B)):
        if A[i] == B[j]:
            dp[i][j] = 1 + dp[i-1][j-1]
        else:
            dp[i][j] = max(dp[i-1][j], dp[i][j-1])
print(dp[-1][-1])