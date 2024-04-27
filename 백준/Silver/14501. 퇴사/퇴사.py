N = int(input())

consult_table = [] # 상담 일정표
for _ in range(N):
    T, P = map(int, input().split())
    consult_table.append((T, P))

visited = [False] * N
max_price = 0

def dfs(start, prices):
    global max_price
    if start >= N:
        if start > N:
            prices.pop()
        if max_price < sum(prices):
            max_price = sum(prices)
        return

    for i in range(start, N):
        if not visited[i]:
            visited[i] = True
            dfs(i + consult_table[i][0], prices + [consult_table[i][1]])
            visited[i] = False

dfs(0, [])
print(max_price)