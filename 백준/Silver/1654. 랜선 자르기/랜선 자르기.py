K, N = map(int, input().split())
lst = []
for _ in range(K):
    lst.append(int(input()))

left = 1
right = max(lst)
# 이분 탐색
while left <= right:
    mid = (left + right) // 2
    count = 0
    for length in lst:
        count += length // mid
    if count >= N:
        left = mid + 1
    else:
        right = mid - 1
print(right)