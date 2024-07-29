N = int(input())
inputs = list(map(int, input().split()))
tree = [[] for _ in range(N)]

for i in range(1, N):
    tree[inputs[i]].append(i)

def dfs(node):
    if not tree[node]:
        return 0
    
    childs = []
    for child in tree[node]:
        childs.append(dfs(child))

    childs.sort(reverse=True)

    time = 1
    for i in range(len(childs)):
        childs[i] += time
        time += 1

    return max(childs)

print(dfs(0))