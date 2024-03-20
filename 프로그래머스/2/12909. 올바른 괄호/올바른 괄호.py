def solution(s):
    stack = []
    for p in s:
        if p == '(':
            stack.append(p)
        else:
            if stack and stack[-1] == '(':
                stack.pop()
            else:
                stack.append(p)
                
    if not stack: return True
    return False