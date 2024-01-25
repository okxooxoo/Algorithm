import math
N, S = map(int, input().split())
lst = list(map(int, input().split()))
for i in range(N):
    lst[i] = abs(lst[i] - S)
print(math.gcd(*lst))