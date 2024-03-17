n = int(input())
P = list(map(int, input().split()))

for i in range(1, n):
    P[i] = max(P[i], P[i-1] + P[i])

print(max(P))