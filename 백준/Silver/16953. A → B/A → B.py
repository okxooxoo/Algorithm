from collections import deque

A, B = map(int, input().split())
# B를 A로 만들자
def bfs(A, B):
    dq = deque([(B, 1)])
    while dq:
        num, depth = dq.popleft()
        if num == A:
            return depth
        if num == 0:
            continue
        # 끝자리가 1이면
        if num % 10 == 1:
            dq.append((num // 10, depth + 1))
        # 짝수면
        if num % 2 == 0:
            dq.append((num // 2, depth + 1))
    return -1

print(bfs(A, B))