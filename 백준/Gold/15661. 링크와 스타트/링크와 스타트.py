import sys
from itertools import combinations
input = sys.stdin.readline

N = int(input())
# 능력치 표
ability_table = [list(map(int, input().split())) for _ in range(N)]
people = [i for i in range(N)]
visited = [False] * N
min_diff = sys.maxsize

def calc_diff():
    start_ability = 0
    link_ability = 0
    for i in range(N):
        for j in range(N):
            if visited[i] and visited[j]:
                start_ability += ability_table[i][j]
            elif not visited[i] and not visited[j]:
                link_ability += ability_table[i][j]

    diff = abs(start_ability - link_ability)
    return diff

for i in range(1, N // 2 + 1):
    for comb in combinations(people, i):
        for c in comb:
            visited[c] = True
        diff = calc_diff()
        if min_diff > diff:
            min_diff = diff
        visited = [False] * N

print(min_diff)