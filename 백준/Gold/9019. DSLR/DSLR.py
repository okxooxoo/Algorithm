from collections import deque

def bfs(A, B):
    dq = deque([(A, '')])
    visited = [False] * 10000
    while dq:
        num, cmd = dq.popleft()
        # 목표 숫자로 변환했으면
        if num == B:
            return cmd
        # 이미 연산 결과로 나온 숫자면
        if visited[num]:
            continue
        # 방문 처리
        visited[num] = True
        # 4가지 연산 실행
        dq.append((D(num), cmd+'D'))
        dq.append((S(num), cmd+'S'))
        dq.append((L(num), cmd+'L'))
        dq.append((R(num), cmd+'R'))

def D(num):
    return num * 2 % 10000

def S(num):
    if num == 0:
        return 9999
    return num - 1

def L(num):
    d1 = str(num // 1000)
    d2 = str((num % 1000) // 100)
    d3 = str((num % 100) // 10)
    d4 = str(num % 10)
    return int(d2 + d3 + d4 + d1)

def R(num):
    d1 = str(num // 1000)
    d2 = str((num % 1000) // 100)
    d3 = str((num % 100) // 10)
    d4 = str(num % 10)
    return int(d4 + d1 + d2 + d3)

T = int(input())
for _ in range(T):
    A, B = map(int, input().split())
    print(bfs(A, B))
