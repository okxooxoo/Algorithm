# 유클리드 호제법
A, B = map(int, input().split())

def GCD(A, B):
    while B > 0:
        A, B = B, A % B
    return A

def LCM(A, B):
    return A * B // GCD(A, B)

print(GCD(A, B))
print(LCM(A, B))