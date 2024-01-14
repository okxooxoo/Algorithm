import sys
input = sys.stdin.readline

N = int(input())
postfix = input().rstrip()

operator = ['+', '-', '*', '/']
operand = []
stack = []

for _ in range(N):
    n = int(input())
    operand.append(n)

for i in postfix:
    if i in operator:
        y = stack.pop()
        x = stack.pop()
        
        if i == '+':
            stack.append(x + y)
        elif i == '-':
            stack.append(x - y)
        elif i == '*':
            stack.append(x * y)
        else:
            stack.append(x / y)
    else:
        stack.append(operand[ord(i) - 65])

print(f"{stack.pop():.2f}")