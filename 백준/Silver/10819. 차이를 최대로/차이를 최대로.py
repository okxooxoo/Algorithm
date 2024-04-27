N = int(input())
lst = list(map(int, input().split()))

visited = [False] * N # 방문 기록
max_sum = 0

def dfs(depth, prev_num, sum):
    global max_sum
    if depth == N:
        if max_sum < sum:
            max_sum = sum
        return
    
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            dfs(depth + 1, lst[i], sum + abs(prev_num - lst[i]))
            visited[i] = False # 백트래킹

for i in range(N):
    visited[i] = True
    dfs(1, lst[i], 0)
    visited[i] = False

print(max_sum)