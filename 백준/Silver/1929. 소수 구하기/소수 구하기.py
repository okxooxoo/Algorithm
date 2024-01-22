M, N = map(int, input().split())

# 에라토스테네스의 체 (True로 초기화, 소수면 True, 소수가 아니면 False)
prime_check = [True] * (N + 1)
# 0과 1은 소수가 아님
prime_check[0] = False
prime_check[1] = False

# N의 제곱근까지 반복(시간 초과 해결)
for i in range(2, int(N ** 0.5) + 1):
    if prime_check[i] == True:
        for j in range(i * 2, N + 1, i):
            prime_check[j] = False

for i in range(M, N + 1):
    if prime_check[i] == True:
        print(i)