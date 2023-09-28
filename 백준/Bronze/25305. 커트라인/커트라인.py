N, k = map(int, input().split())
lst = list(map(int, input().split()))
# sort()를 사용하지 않고 내림차순으로 정렬
for i in range(N-1):
    for j in range(N-i-1):
        if lst[j] < lst[j+1]:
            lst[j], lst[j+1] = lst[j+1], lst[j]
print(lst[k-1])