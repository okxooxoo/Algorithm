A = input()
B = input()
N = len(A) - len(B)

index = 0
count = 0
while index <= N:
    if A[index:index+len(B)] == B:
        count += 1
        index += len(B)
    else:
        index += 1
print(count)