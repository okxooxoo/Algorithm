N = int(input())
factorial = 1
if N > 1:
    for i in range(2, N + 1):
        factorial *= i

factorial_lst = reversed(list(str(factorial)))
count = 0
for i in factorial_lst:
    if i != '0':
        break
    count += 1
print(count)