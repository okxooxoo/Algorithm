N = int(input())

P = []
def dfs():
    if len(P) == N:
        print(*P)
        return
    
    for i in range(1, N+1):
        if i not in P:
            P.append(i)
            dfs()
            P.pop()

dfs()