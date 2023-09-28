N = int(input())
lst = []
for _ in range(N):
    n = int(input())
    lst.append(n)
# lst = sorted(lst)
for i in range(N-1):
    for j in range(N-1-i):
        if lst[j] > lst[j+1]:
            lst[j], lst[j+1] = lst[j+1], lst[j]
for n in lst:
    print(n)