a1, a2 = map(int, input().split())
c = int(input())
n0 = int(input())
if a1*n0 + a2 <= n0*c and a1 <= c: # 조건(1)과 조건(2)를 모두 만족해야 1을 출력
    print(1)
else:
    print(0)

# a1이 양수이고 a0이 음수인 경우에는 조건(1)을 만족한다고 하더라도
# 모든 n >= n0에 대하여 f(n) <= c x g(n)이 성립하지 않는다.