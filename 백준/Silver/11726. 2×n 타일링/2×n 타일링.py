n = int(input())
lst = [0, 1, 2]
for i in range(3, n + 1):
    lst.append((lst[i-1] + lst[i-2]) % 10007)
print(lst[n])