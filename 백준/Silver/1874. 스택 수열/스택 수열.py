import sys
input = sys.stdin.readline

n = int(input())
seq = []
sign = [] # '+' 또는 '-'

for _ in range(n):
    s = int(input())
    seq.append(s)

def myFunc(seq):
    stack = []
    index = 0 # 지금까지 스택에 추가된 가장 높은 정수
    for s in seq:
        if s > index:
            for i in range(index, s):
                stack.append(i + 1)
                sign.append('+')
            index = s
            stack.pop()
            sign.append('-')
        else:
            if s == stack[-1]:
                stack.pop()
                sign.append('-')
            else:
                return False
    return True

if myFunc(seq):
    for i in sign:
        print(i)
else:
    print('NO')