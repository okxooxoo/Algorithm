T = int(input())
for _ in range(T):
    closet = {}
    n = int(input())
    for _ in range(n):
        clothes, kind = input().split()
        closet[kind] = closet.get(kind, 0) + 1
    result = 1
    for num in closet.values():
        result *= num + 1
    if n == 0:
        print(0)
    else:
        print(result - 1)
