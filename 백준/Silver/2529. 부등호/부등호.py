import sys

N = int(input()) # 부등호 개수
lst = list(input().split())

visited = [False] * 10
number = []
mn = sys.maxsize
mx = 0

def dfs(depth):
    global mn
    global mx

    if depth == N:
        n = ''.join(map(str, number))
        if int(mn) > int(n):
            mn = n
        if int(mx) < int(n):
            mx = n
        return
    
    for i in range(10):
        if not visited[i]:
            if (lst[depth] == '<' and number[-1] < i) \
                or (lst[depth] == '>' and number[-1] > i):
                visited[i] = True
                number.append(i)
                dfs(depth + 1)
                visited[i] = False
                number.pop()


for i in range(10):
    visited[i] = True
    number.append(i)
    dfs(0)
    visited[i] = False
    number.pop()

print(mx)
print(mn)