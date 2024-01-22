T = int(input())

def GCD(A, B):
    while B > 0:
        A, B = B, A % B
    return A

def LCM(A, B):
    return A * B // GCD(A, B)

for _ in range(T):
    A, B = map(int, input().split())
    print(LCM(A, B))