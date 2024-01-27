import sys
input = sys.stdin.readline
# 백만 이하의 모든 짝수에 대하여 검증
N = 1000000
prime_check = [True] * (N + 1)
# 0과 1은 소수가 아님
prime_check[0] = False
prime_check[1] = False

for i in range(2, int(N ** 0.5) + 1):
    if prime_check[i]:
        for j in range(i * 2, N + 1, i):
            prime_check[j] = False

T = int(input())
for _ in range(T):
    N = int(input())
    sum = 0
    for i in range(2, N // 2 + 1):
        if prime_check[i] and prime_check[N-i]:
            sum += 1
    print(sum)