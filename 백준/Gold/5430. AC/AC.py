import sys
from collections import deque
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    p = list(input().rstrip())
    n = int(input())
    if n == 0:
        X = deque(input().rstrip()[1:-1])
    else:
        X = deque(map(int, input().rstrip()[1:-1].split(',')))

    rvs = False # 뒤집혔는지 여부
    err = False # 에러 발생 여부
    
    for f in p:
        if f == 'R':
            rvs = not rvs
        elif f == 'D' and X:
            if rvs:
                X.pop()
            else:
                X.popleft()
        else:
            err = True
            break
    if err:
        print('error')
    else:
        if rvs:
            X.reverse()
        print('[' + ','.join(map(str,X)) + ']')