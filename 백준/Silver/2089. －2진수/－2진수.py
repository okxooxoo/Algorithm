N = int(input())
if N == 0:
    print(0)
else:
    result = ''
    while N:
        result += str(N % 2)
        N //= 2
        N *= -1
    print(result[::-1])