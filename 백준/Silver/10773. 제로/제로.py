K = int(input())
stack = []
for i in range(K):
    N = input()
    if N == '0':
        stack.pop()
    else:
        stack.append(int(N))
print(sum(stack))