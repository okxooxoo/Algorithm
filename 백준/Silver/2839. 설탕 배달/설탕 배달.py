N = int(input())
count = 0
while N > 0:
    if N % 5 == 0:
        break
    else:
        N -= 3
        count += 1
if N < 0:
    answer = -1
else:
    answer = N // 5 + count
print(answer)