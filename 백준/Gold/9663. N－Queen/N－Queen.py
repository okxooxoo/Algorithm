N = int(input())

# 하나의 행에 하나의 퀸만 위치할 수 있음!
choosed = [0] * N # 선택한 칸
count = 0

def valid_queen(r, c):
    for i in range(r):
        if c == choosed[i] or abs(r - i) == abs(c - choosed[i]):
            return False
    return True

def makeCombination(depth):
    global count

    if depth == N:
        count += 1
        return
    
    for c in range(N):
        if valid_queen(depth, c):
            choosed[depth] = c
            makeCombination(depth + 1)

makeCombination(0)
print(count)