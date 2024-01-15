import sys
input = sys.stdin.readline

while True:
    str = input().rstrip('\n')
    if not str:
        break

    l, u, d, s = 0, 0, 0, 0
    for i in str:
        if i.islower():
            l += 1
        elif i.isupper():
            u += 1
        elif i.isdigit():
            d += 1
        elif i.isspace():
            s += 1
    print(l, u, d, s)