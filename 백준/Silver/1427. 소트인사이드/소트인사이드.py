N = list(input())
for i in range(len(N)-1):
    for j in range(len(N)-i-1):
        if int(N[j]) < int(N[j+1]):
            N[j], N[j+1] = N[j+1], N[j]
print(*N, sep='')