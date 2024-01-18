A, B = map(int, input().split())

for i in range(A, 0, -1):
    if A % i == 0 and B % i == 0:
        GCF = i # 최대공약수
        C = A // i # 서로소
        D = B // i # 서로소
        break
LCM = GCF * C * D # 최소공배수 = 최대공약수 * 서로소

print(GCF)
print(LCM)