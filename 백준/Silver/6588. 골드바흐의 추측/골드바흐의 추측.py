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

while True:
    n = int(input())
    if n == 0:
        break
    for i in range(3, N // 2 + 1, 2):
        if prime_check[i] == True and prime_check[n-i] == True:
            print(f'{n} = {i} + {n-i}')
            break
    else:
        print("Goldbach\'s conjecture is wrong.")