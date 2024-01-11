import sys
input = sys.stdin.readline

N = int(input())
lst = list(map(int, input().split()))

count = [0] * 1000001 # 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)
for i in lst:
    count[i] += 1

stack = []
result = [-1] * N
for i in range(N):
    while stack and count[lst[i]] > count[lst[stack[-1]]]:
        result[stack.pop()] = lst[i]
    stack.append(i)

print(*result)