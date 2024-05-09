from collections import deque

N, K = map(int, input().split())
lst = deque([i for i in range(1, N+1)])
josephus = []
while lst:
    for _ in range(K-1):
        lst.append(lst.popleft())
    josephus.append(lst.popleft())
print('<', end='')
print(', '.join(map(str, josephus)), end='')
print('>', end='')