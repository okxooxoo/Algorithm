from collections import deque

T = int(input())
for _ in range(T):
    N, M = map(int, input().split())
    lst = list((map(int, input().split())))
    count = 0 # 몇 번째로 인쇄되는지 카운트
    while M >= 0:
        if lst[0] == max(lst): # 중요도가 가장 높으면
            lst.pop(0)
            M -= 1
            count += 1
        else:
            lst.append(lst.pop(0))
            if M == 0:
                M = len(lst) - 1
            else:
                M -= 1
    print(count)