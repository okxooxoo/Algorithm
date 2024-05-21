import sys
input = sys.stdin.readline

N, M = map(int, input().split())
lst = list(map(int, input().rstrip().split()))
sum_lst = [0]
s = 0
# 누적합 미리 구하기
for n in lst:
    s += n
    sum_lst.append(s)
for _ in range(M):
    i, j = map(int, input().split())
    print(sum_lst[j]-sum_lst[i-1])