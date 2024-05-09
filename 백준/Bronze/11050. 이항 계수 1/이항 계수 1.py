N, K = map(int, input().split())
# N개 중 K개를 뽑는 조합
A = 1
B = 1
for i in range(N+1-K, N+1):
    A *= i
for i in range(2, K+1):
    B *= i
print(A // B)