N, M = map(int, input().split())
trees = list(map(int, input().split()))
# 이진탐색
start, end = 0, max(trees)
while start <= end:
    mid = (start + end) // 2
    s = sum(tree - mid if tree > mid else 0 for tree in trees)
    if s >= M:
        start  = mid + 1
    else:
        end = mid - 1
print(end)