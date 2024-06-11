N = int(input())
M = int(input())
K = 2 * N + 1
string = list(input())
string.reverse()
sum = 0
while string:
    stack = []
    temp = string.pop()
    if temp == 'I':
        stack.append(temp)
        while string and stack[-1] != string[-1]:
            stack.append(string.pop())
        if stack[-1] == 'O':
            stack.pop()
        if (len(stack) - K) >= 0:
            sum += (len(stack) - K) // 2 + 1
print(sum)