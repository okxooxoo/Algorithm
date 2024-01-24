import math
import sys
input = sys.stdin.readline

t = int(input())
for _ in range(t):
    n, *lst = map(int, input().split())
    sum = 0
    for i in range(len(lst) - 1):
        for j in range(i + 1, len(lst)):
            sum += math.gcd(lst[i], lst[j])
    print(sum)