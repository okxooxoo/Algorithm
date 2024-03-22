def solution(m, n, puddles): # m이 x축, n이 y축
    dp = [[0 for x in range(m + 1)] for y in range(n + 1)]
    dp[1][1] = 1
    for y in range(1, n + 1):
        for x in range(1, m + 1):
            if [x, y] not in puddles:
                if y+1 <= n:
                    dp[y+1][x] += dp[y][x]
                if x+1 <= m:
                    dp[y][x+1] += dp[y][x]
    answer = dp[n][m] % 1_000_000_007
    return answer