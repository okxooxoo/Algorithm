N, M = map(int, input().split())
lst = list(map(int, input().split()))
mx = M
for i in range(N-2):
    for j in range(i+1, N-1):
        for k in range(j+1, N):
            tmp = lst[i] + lst[j] + lst[k]
            if M - tmp < mx and tmp <= M:
                mx = M - tmp
print(M-mx)