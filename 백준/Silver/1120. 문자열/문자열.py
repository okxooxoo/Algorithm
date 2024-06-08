A, B = input().split()
N = len(B) - len(A) # A와 B 길이의 차이

def get_diff(A, B):
    count = 0
    for i in range(len(A)):
        if A[i] != B[i]:
            count += 1
    return count

mn = 50
for i in range(N + 1):
    count = get_diff(A, B[i:i+len(A)])
    if mn > count:
        mn = count
print(mn)