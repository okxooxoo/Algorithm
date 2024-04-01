def solution(A,B):
    A.sort() # 오름차순
    B.sort(reverse=True) # 내림차순
    s = 0
    for i in range(len(A)):
        s += A[i] * B[i]
    return s