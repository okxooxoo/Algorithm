import sys
input = sys.stdin.readline

while True:
    inputs = input().rstrip()
    if inputs == '.':
        break
    stack = []
    for i in list(inputs):
        if i == '(' or i == '[':
            stack.append(i)
        elif i == ')':
            if stack and stack[-1] == '(':
                stack.pop()
            else:
                stack.append(i)
                break
        elif i == ']':
            if stack and stack[-1] == '[':
                stack.pop()
            else:
                stack.append(i)
                break
    if stack:
        print('no')
    else:
        print('yes')