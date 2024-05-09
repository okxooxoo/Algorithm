import sys
input = sys.stdin.readline

def round(number):
    if number - int(number) >= 0.5:
        return int(number) + 1
    return int(number)

n = int(input())
if n == 0:
    print(0)
else:
    lst = []
    for _ in range(n):
        lst.append(int(input()))
    lst.sort()
    ex = round(n * 0.15)
    s = 0
    for i in range(ex, n-ex):
        s += lst[i]
    l = n - 2 * ex
    if l == 0:
        print(0)
    else:
        print(round(s / l))