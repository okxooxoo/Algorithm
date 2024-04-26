N = int(input())
lst = list(map(int, input().split()))

for i in range(N-1, 0, -1):
    if lst[i] < lst[i-1]:
        for j in range(N-1, 0, -1):
            if lst[j] < lst[i-1]:
                lst[j], lst[i-1] = lst[i-1], lst[j]
                lst = lst[:i] + sorted(lst[i:], reverse=True)
                break
        print(*lst)
        break
else:
    print(-1)