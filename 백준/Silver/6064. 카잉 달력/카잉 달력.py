import sys
input = sys.stdin.readline

T = int(input())

for _ in range(T):
    M, N, x, y = map(int, input().split())

    year = x

    if x == M: x -= M
    if y == N: y -= N

    while True:
        if year > M * N:
            year = -1
            break
        if year % N == y:
            break
        year += M

    print(year)