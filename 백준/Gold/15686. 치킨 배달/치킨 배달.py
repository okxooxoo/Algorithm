from collections import deque
import sys

N, M = map(int, input().split())
house = []
chicken = []
open_chicken = [[False] * N for _ in range(N)]
moves = [(-1, 0), (1, 0), (0, -1), (0, 1)]
min_chicken_distance = sys.maxsize

# 입력
for r in range(N):
    row = list(map(int, input().split()))
    for c in range(N):
        if row[c] == 1:
            house.append((r, c))
        elif row[c] == 2:
            chicken.append((r, c))

def make_combination(depth, start):
    global min_chicken_distance
    
    if depth == M:
        # 치킨 거리 계산
        distance = 0
        for h in house:
            distance += calc_chicken_distance(h)
        if min_chicken_distance > distance:
            min_chicken_distance = distance
        return
    
    for i in range(start, len(chicken)):
        r = chicken[i][0]
        c = chicken[i][1]
        open_chicken[r][c] = True
        make_combination(depth + 1, i + 1)
        open_chicken[r][c] = False

def calc_chicken_distance(start):
    distance = sys.maxsize
    for r, c in chicken:
        if open_chicken[r][c]:
            temp = abs(start[0]-r) + abs(start[1]-c)
            if distance > temp:
                distance = temp
    return distance

make_combination(0, 0)
print(min_chicken_distance)