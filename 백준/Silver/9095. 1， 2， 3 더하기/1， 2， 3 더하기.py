lst = [0, 1, 2, 4]
for i in range(4, 11):
    lst.append(lst[i-1] + lst[i-2] + lst[i-3])

T = int(input())
for _ in range(T):
    n = int(input())
    print(lst[n])