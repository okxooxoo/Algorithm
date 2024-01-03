import sys
sys = sys.stdin.readline

def isVPS(s):
    stack = []
    for i in s:
        if i == '(':
            stack.append(i)
        if i == ')':
            if len(stack) > 0:
                stack.pop()
            else:
                return 'NO'
    if len(stack) == 0:
        return 'YES'
    else:
        return 'NO'
        
T = int(input())
for _ in range(T):
    s = input().rstrip()
    result = isVPS(s)
    print(result)