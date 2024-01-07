S = input()
stack = []
count = 0
isOpen = False
for s in S:
    if s == '(':
        stack.append(s)
        isOpen = True
    if s == ')':
        stack.pop()
        if isOpen:
            count += len(stack)
        else:
            count += 1
        isOpen = False
print(count)