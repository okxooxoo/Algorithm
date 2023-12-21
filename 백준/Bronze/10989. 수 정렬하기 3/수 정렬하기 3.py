import sys
input = sys.stdin.readline

N = int(input())
counts = [0] * (10001) # 입력될 숫자는 10000 이하의 범위

for _ in range(N):
    counts[int(input())] += 1

for i in range(10001):
    for j in range(counts[i]):
        print(i)
