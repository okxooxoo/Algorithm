S = list(input())
stack = []
postfix = []

for s in S:
    if s == '(':
        stack.append(s)
    elif s == ')':
        while stack and stack[-1] != '(':
            postfix.append(stack.pop())
        stack.pop() # '(' 제거
    elif s in ['*', '/']:
        while stack and stack[-1] in ['*', '/']:
            postfix.append(stack.pop())
        stack.append(s)
    elif s in ['+', '-']:
        while stack and stack[-1] != '(':
            postfix.append(stack.pop())
        stack.append(s)
    else:
        postfix.append(s) # 피연산자

while stack:
    postfix.append(stack.pop())

print("".join(postfix))