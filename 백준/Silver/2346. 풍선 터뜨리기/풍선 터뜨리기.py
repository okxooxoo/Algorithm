from collections import deque

N = int(input())
inputs = list(map(int, input().split()))
balloon = deque()

for i in range(N):
    # 풍선 번호와 종이에 적힌 값
    balloon.append((i+1, inputs[i]))

answer = []

while balloon:
    index, delta = balloon.popleft()
    answer.append(index)
    if balloon:
        # 오른쪽으로 이동해야 하면
        if delta > 0:
            for _ in range(delta - 1):
                balloon.append(balloon.popleft())
        # 왼쪽으로 이동해야 하면
        else:
            for _ in range(-delta):
                balloon.appendleft(balloon.pop())

print(*answer)
    