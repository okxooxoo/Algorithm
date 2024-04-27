from itertools import combinations

while True:
    k, *S = map(int, input().split())
    if k == 0:
        break

    for c in combinations(S, 6):
        print(*c)
    print()