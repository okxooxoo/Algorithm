N = int(input())

count = N
mul = 10
while N >= mul:
    count += (N - mul + 1)
    mul *= 10

print(count)