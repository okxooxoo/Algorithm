from collections import deque

T = int(input())
for _ in range(T):
    N, M = map(int, input().split())
    dq = deque((map(int, input().split())))
    count = 0 # 몇 번째로 인쇄되는지 카운트
    while M >= 0:
        if dq[0] == max(list(dq)): # 중요도가 가장 높으면
            dq.popleft()
            M -= 1
            count += 1
        else:
            dq.append(dq.popleft())
            if M == 0:
                M = len(dq) - 1
            else:
                M -= 1
    print(count)