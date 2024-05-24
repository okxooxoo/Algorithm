N, R, C = map(int, input().split())

count = 0
while N > 0:
    if R < (2 ** N) // 2:
        if C < (2 ** N) // 2:
            pass
        else:
            count += (4 ** (N-1)) * 1
            C -= (2 ** N) // 2
    else:
        if C < (2 ** N) // 2:
            count += (4 ** (N-1)) * 2
            R -= (2 ** N) // 2
        else:
            count += (4 ** (N-1)) * 3
            R -= (2 ** N) // 2
            C -= (2 ** N) // 2
    N -= 1

print(count)