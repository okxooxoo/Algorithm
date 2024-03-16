N, M = map(int, input().split())
A = []
B = []
# 행렬 A 저장
for _ in range(N):
    row = list(map(int, list(input())))
    A.append(row)
# 행렬 B 저장
for _ in range(N):
    row = list(map(int, list(input())))
    B.append(row)

count = 0
for n in range(N-2):
    for m in range(M-2):
        if A[n][m] != B[n][m]:
            count += 1
            for i in range(3):
                for j in range(3):
                    A[n+i][m+j] = 1 - A[n+i][m+j]

if A == B: # 두 행렬이 같으면
    print(count)
else:
    print(-1)