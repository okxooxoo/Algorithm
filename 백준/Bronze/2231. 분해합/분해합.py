N = int(input())
result = 0
for i in range(N):
    Sum = i
    for j in str(i):
        Sum += int(j)
    if Sum == N:
        result = i
        break
print(result)